layui.define(['element', 'common'], function (exports) {
    "use strict";

    function Menu() {
        this.option = null;
        this.menu = {};
        this.init = function (option) {
            this.option = formatOption(option);
            if ('' != this.option.id) {
                this.menu = $('#' + this.option.id);
                this.menu.empty();
            }
        }

        this.load = function (datas) {
            if (datas.length > 1) {
                createItem(this.menu, datas);
                this.attachEvent(this.menu);
                layui.element.init();
            }
        }
        this.attachEvent = function (me) {
            me.find("a").on('click', function (e) {
                var event = {"sender": menu, "e": e, "target": this};
                if (menu.option.itemClick) {
                    menu.option.itemClick(event);
                }
            });


        }

        function createItem(menu, datas) {
            var itemtemp = '<li class="layui-nav-item"></li>';

            for (var i = 0; i < datas.length; i++) {
                var data = $.extend(new MenuEntity(), datas[i]);
                var item = $(itemtemp).append(createTagA(data));
                ;

                if (data.children.length > 1) {
                    item.append(createChildren(data.children));
                }
                menu.append(item);
            }
        }

        function createChildren(datas) {
            var childtem = '<dl class="layui-nav-child"></dl>';
            var childitem = '<dd></dd>';
            var child = $(childtem);
            for (var i = 0; i < datas.length; i++) {
                var data = $.extend(new MenuEntity(), datas[i]);
                var item = $(childitem).append(createTagA(data));

                child.append(item);
            }
            return child;
        }

        function createTagA(data) {
            var taga = '<a href="javascript:;"  url="{url}" openType="{openType}" >{name}</a>';

            taga = taga.replace(/{name}/g, (undefined != data.name && null != data.name) ? data.name : "");


            taga = taga.replace(/{url}/g, (undefined != data.url && null != data.url) ? data.url : "");


            taga = taga.replace(/{openType}/g, (undefined != data.openType && null != data.openType) ? data.openType : "");
            return taga;
        }

        function formatOption(option) {
            var opt = {"id": "", "itemclick": null, "itemCls": ""};
            return $.extend(opt, option);
        }

        function MenuEntity() {
            this.name = "";
            this.url = "";
            this.children = [];
            this.openType = "_self";
        }

    }

    exports('menu', function (option) {
        var menu = new Menu();
        menu.init(option);
        return menu;
    });
});