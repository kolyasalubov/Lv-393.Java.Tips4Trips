var fullHeight = function() {

    $('.js-fullheight').css('height', $(window).height()/2);
    $(window).resize(function(){
        $('.js-fullheight').css('height', $(window).height()/2);
    });

};
fullHeight();