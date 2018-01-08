 var crs = new L.Proj.CRS('EPSG:900913',
            '+proj=merc +a=6378206 +b=6356584.314245179 +lat_ts=0.0 +lon_0=0.0 +x_0=0 +y_0=0 +k=1.0 +units=m +nadgrids=@null +wktext  +no_defs',
            {
                resolutions: function () {
                    level = 19
                    var res = [];
                    res[0] = Math.pow(2, 18);
                    for (var i = 1; i < level; i++) {
                        res[i] = Math.pow(2, (18 - i))
                    }
                    return res;
                }(),
                origin: [0,0],
                bounds: L.bounds([20037508.342789244, 0], [0, 20037508.342789244])
            }),
            map = L.map('map', {
                crs: crs
            });

    new L.TileLayer('http://online{s}.map.bdimg.com/tile/?qt=tile&x={x}&y={y}&z={z}&styles=pl&udt=20150518', {
        maxZoom: 18,
        minZoom: 3,
        subdomains: [0,1,2],
        attribution: 'ⓒ 2012 Daum',
        tms: true
    }).addTo(map);

    //Gunsan Airport
    
    new L.marker([39.915052,116.403954]).addTo(map);
    new L.marker([31.228753,121.479802]).addTo(map);
    new L.marker([31.221515,121.481428]).addTo(map);
    new L.marker([23.071932,113.219245]).addTo(map);
    new L.marker([43.918961,87.597538]).addTo(map);
    new L.marker([47.385302,123.932747]).addTo(map);
    new L.marker([29.622349,91.050216]).addTo(map);

    

    

    

    map.setView([39.915052,116.403954], 15);