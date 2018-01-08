var searchboxControl = L.Control.extend({
    options: {
        position: 'topleft'
    },
    getControlHrmlContent: function () {

        var controlHtmlContent = "<div id=\"controlbox\" >" +
            "<div id=\"boxcontainer\" class=\"searchbox searchbox-shadow\" > " +
            "<div><input id=\"searchboxinput\" placeholder='${emptyText}' type=\"text\" style=\"position: relative;\"\/><\/div>" +
            "<div class=\"searchbox-searchbutton-container\"><button aria-label=\"search\" id=\"searchbox-searchbutton\" class=\"searchbox-searchbutton\"><\/button> <span aria-hidden=\"true\" style=\"display:none;\">search<\/span> <\/div><\/div>" +
            "<div class=\"resultPanel\"><ul><li><div class='resultPanelItem resultPanelItem-icon'><span class='resultPanelItem-text'>fdsafasdfdas</span></div></li><li><div class='resultPanelItem resultPanelItem-icon'><span class='resultPanelItem-text'>fdsafasdfdas</span></div></li></ul><\/div>" +
            "<div class=\"autoCompletePanel\"><ul></ul><\/div>" +
            "<\/div>";
        if (!this.m_EmptyInput || null == this.m_EmptyInput) {
            this.m_EmptyInput = "";
        }
        controlHtmlContent = controlHtmlContent.replace("${emptyText}", this.m_EmptyInput);
        return controlHtmlContent;
    },
    renderAutoCompleteResult: function (data) {
        var ul = $(".autoCompletePanel ul");
        ul.empty();

        var that = this;
        var li = "<li><div class='autoCompleteItem autoCompleteItem-icon'><span class='autoCompleteItem-text'>${text}</span></div></li>";

        for (var i = 0; i < data.length; i++) {
            ul.append(li.replace("${text}", data[i]["name"]));
        }

        setTimeout(function () {
            $(".autoCompletePanel ul li").on("click", function () {
                if (that.m_SearchKeyItemClick && "function" == typeof that.m_SearchKeyItemClick) {
                    var event = {"sender": that, "source": this};
                    that.m_SearchKeyItemClick(event);
                }
                else {
                    var value = $(this).find(".autoCompleteItem-text").text();
                    $("#searchboxinput").val(value);
                    that.doSearch(value);
                }
            });
        }, 1);
    },
    initialize: function (options) {
        L.Util.setOptions(this, options);
        this.m_Url = options.url;
        this.m_Param = options.param;
        this.m_KeyUrl = options.keyUrl;
        this.m_KeyParam = options.keyParam;
        this.m_SearchResultItemClick = options.searchResultItemClick;
        this.m_SearchKeyItemClick = options.searchKeyItemClick;
        this.m_EmptyInput = options.emptyInput;
        this.m_markerLayer = L.layerGroup();
        if (options.map) {
            this.m_Map=options.map;
            this.m_markerLayer.addTo(options.map);
        }
    },
    doKeySearch: function (text, sucess) {
        var postdata = $.extend({}, this.m_KeyParam);
        postdata["text"] = text;
        $.ajax({
            "url": this.m_KeyUrl,
            type: "post",
            "dataType": "json",
            data: postdata,
            "success": sucess,
            error: function (e) {
            },
            complete: function (xhr, tx) {
            }
        });

    },
    doSearch: function (text) {
        var that = this;
        var postdata = $.extend({}, this.m_Param);
        postdata["text"] = text;
        $.ajax({
            "url": this.m_Url,
            type: "post",
            "dataType": "json",
            data: postdata,
            "success": function (data) {
                $(".autoCompletePanel").fadeOut();
                that.renderSearchResultMarker(data);
                that.renderSearchResult(data);
                $(".resultPanel").fadeIn();
            },
            error: function (e) {
            },
            complete: function (xhr, tx) {
            }
        });
    },
    renderSearchResultMarker :function(data){
        this.m_markerLayer.clearLayers();
        for(var i=0;i<data.length;i++)
        {
            var marker= L.marker([data[i]["lat"],data[i]["lng"]]);
            marker.addTo(this.m_markerLayer)
                .bindPopup(data[i]["name"]);
        }
    },
    renderSearchResult: function (data) {
        var that = this;
        var li = "<li><div class='resultPanelItem resultPanelItem-icon'><span class='resultPanelItem-text'>${text}</span></div></li>";
        var ul = $(".resultPanel ul");
        ul.empty();
        if (data && data.length < 1) {
            ul.append(li.replace("${text}", "没有查到结果"));
            return;
        }

        for (var i = 0; i < data.length; i++) {
            var litag=li.replace("${text}", data[i]["name"]);
            var liobj= $(litag);
            liobj.data(data[i]);
            ul.append(liobj);
        }

        setTimeout(function () {
            $(".resultPanel ul li").on("click", function () {
                if (that.m_SearchResultItemClick && "function" == typeof that.m_SearchResultItemClick) {
                    var event = {"sender": that, "source": this}
                    that.m_SearchResultItemClick(event);
                }else {
                    var data=$(this).data();
                    that.m_markerLayer.eachLayer(function(layer){
                        var point=layer.getLatLng();
                        if(point.lat==data.lat&&point.lng==data.lng)
                        {
                            if(that.m_Map)
                            {that.m_Map.setView(point);}
                            layer.openPopup();
                        }
                    });
                }
            });

        }, 1);
    },
    onAdd: function (map) {
        var container = L.DomUtil.create('div');
        container.id = "controlcontainer";

        $(container).html(this.getControlHrmlContent());
        var that = this;
        setTimeout(function () {
            $("#searchboxinput").on("keyup", function () {
                that.doKeySearch($("#searchboxinput").val(), function (data) {
                    that.renderAutoCompleteResult(data);
                    $(".autoCompletePanel").fadeIn();

                });
            });
            $("#searchboxinput").on("blur", function () {
                $(".autoCompletePanel").fadeOut();
            });

            $("#searchbox-searchbutton").click(function () {
                var searchkeywords = $("#searchboxinput").val();
                if (!searchkeywords) {
                    $(".resultPanel").fadeOut();
                    return
                }
                ;
                that.doSearch(searchkeywords);
            });
        }, 1);


        L.DomEvent.disableClickPropagation(container);
        return container;
    }
});
