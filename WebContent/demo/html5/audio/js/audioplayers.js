(function (exports) {

    var AudioPlayers = function (config) {
        var players=[];

        this.playUrl=function(url){
            var audio=getOnePlayer();
            audio.src=url;
            audio.play();
        }
        this.playData=function(data){
            var url = window.URL.createObjectURL(data);
            this.playUrl(url);
        }
        function getOnePlayer()
        {
            for(var i=0;i<players.length;i++)
            {
                if(players[i].paused)
                {
                    return players[i];
                }
            }
            return createPlayer();
        }
        function createPlayer()
        {
            var audio =new Audio();
            players[players.length]=audio;
            return audio;
        }
    }

    exports.AudioPlayers = AudioPlayers;
})(window);