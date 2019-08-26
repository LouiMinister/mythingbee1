// 기본 세팅
$(document).ready(function() {

    var resizeTimer;
    // =========================== 브라우저 크기
    var defaultWidth = $(window).width();
    var defaultHeight = $(window).height();

    onSize();

    $(window).on('resize', function (e) {
        defaultWidth = $(window).width();
        defaultHeight = $(window).height();
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(function () {
            location.reload();
        }, 150);
    });

    function onSize() {
        var finalHeight = defaultHeight - 120;
        console.log(defaultHeight);
        if (defaultHeight < 900) {
            $('#gradient').css('top', '800px');
        } else {
            $('#gradient').css('top', (finalHeight - 70) + 'px');
        }

        var finalposition = defaultWidth - 380;
        $('#gradient').css('left', finalposition + 'px');
    }
});
