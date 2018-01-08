var term;


function getURL () {

    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： cis/website/meun.htm
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8080
    var localhostPaht = curWwwPath.substring(0, pos); //获取带"/"的项目名，如：/cis
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    var rootPath = localhostPaht + projectName;
    return rootPath;
};
var url=getURL();
url=url.replace("http://","");
var wsUrl = "ws://"+url+"/myrest/mywebsocket"
websocket = new WebSocket(wsUrl);//new 一个websocket实例
var name=window.prompt("输入要连接的服务器格式为：user,pwd,host或者user,pwd,host,port","");

websocket.onopen = function(evt) {//打开连接websocket
    term = new Terminal({  //new 一个terminal实例，就是数据展示的屏幕和一些见简单设置，包括屏幕的宽度，高度，光标是否闪烁等等
        cols: 100,
        rows: 200,
        screenKeys: true,
        useStyle: true,
        cursorBlink: true,
    });
    websocket.send(name);

    /*term实时监控输入的数据，并且websocket把实时数据发送给后台*/
    term.on('data', function(data) {//term.on方法就是实时监控输入的字段，
        websocket.send(data);//websocket发送给后台
    });
    term.on('title', function(title) {
        //document.title = title;
    });
    term.open(document.getElementById('container-terminal'));//屏幕将要在哪里展示，就是屏幕展示的地方

    websocket.onmessage = function(evt) {//接受到数据
        term.write(evt.data);//把接收的数据写到这个插件的屏幕上
    }
    websocket.onclose = function(evt) {//websocket关闭
        term.write("\r\n连接已关闭，请刷新页面重新连接");
        //term.destroy();//屏幕关闭
    }
    websocket.onerror = function(evt) {//失败处理
        alert(evt);
        if (typeof console.log == "function") {
            console.log(evt)
        }
    }
}

var close = function() {//关闭websocket
    websocket.close();
};


var menu = document.getElementById("menu");
document.oncontextmenu = function(ev) {
    var oEvent = ev || event;
    //自定义的菜单显示
    menu.style.display = "block";
    //让自定义菜单随鼠标的箭头位置移动
    menu.style.left = oEvent.clientX + "px";
    menu.style.top = oEvent.clientY + "px";
    //return false阻止系统自带的菜单，
    //return false必须写在最后，否则自定义的右键菜单也不会出现
    return false;

}
//实现点击document，自定义菜单消失
document.onclick = function() {

    menu.style.display = "none";
};
function paste()
{
    var name=window.prompt("执行自定义命令","");
    websocket.send(name);
};



