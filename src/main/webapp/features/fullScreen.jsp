<a id="btnFullscreen" type="button" align="right"><i class="fas fa-expand-arrows-alt" style="position: absolute; bottom: 20px; left: 20px"></i></a>

<script>
    function toggleFullscreen(elem) {
        elem = elem || document.documentElement;

        if (!document.fullscreenElement && !document.mozFullScreenElement &&
            !document.webkitFullscreenElement && !document.msFullscreenElement) {
            if (elem.requestFullscreen) {
                elem.requestFullscreen();
            } else if (elem.msRequestFullscreen) {
                elem.msRequestFullscreen();
            } else if (elem.mozRequestFullScreen) {
                elem.mozRequestFullScreen();
            } else if (elem.webkitRequestFullscreen) {
                elem.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
            }
        } else {
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitExitFullscreen) {
                document.webkitExitFullscreen();
            }
        }
    }

    document.getElementById('btnFullscreen').addEventListener('click', function() {
        toggleFullscreen();
    });

    document.getElementById('calendar').addEventListener('click', function() {
        toggleFullscreen(this);
    });
</script>


<style>
    #exampleImage { cursor:zoom-in; }
    #exampleImage:-webkit-full-screen { cursor:zoom-out; }
    #exampleImage:-moz-full-screen { cursor:zoom-out; }
    #exampleImage:-ms-fullscreen { cursor:zoom-out; }
    #exampleImage:fullscreen { cursor:zoom-out; }
</style>
