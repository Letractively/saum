(function($) {
    $.jQTouch = function(options) {
        $.support.WebKitCSSMatrix = (typeof WebKitCSSMatrix == "object");
        $.support.touch = (typeof Touch == "object");
        $.support.WebKitAnimationEvent = (typeof WebKitTransitionEvent == "object");
        var $body, 
            $head=$('head'), 
            hist=[], 
            newPageCount=0, 
            jQTSettings={}, 
            hashCheck, 
            currentPage, 
            orientation, 
            isMobileWebKit = RegExp(" Mobile/").test(navigator.userAgent), 
            tapReady=true,
            lastAnimationTime=0,
            touchSelectors=[],
            publicObj={},
            extensions=$.jQTouch.prototype.extensions,
            defaultAnimations=['slide','flip','slideup','swap','cube','pop','dissolve','fade','back'], 
            animations=[], 
            hairextensions='';
        init(options);

        function init(options) {
            
            var defaults = {
                addGlossToIcon: true,
                backSelector: '.back, .cancel, .goback',
                cacheGetRequests: true,
                cubeSelector: '.cube',
                dissolveSelector: '.dissolve',
                fadeSelector: '.fade',
                fixedViewport: true,
                flipSelector: '.flip',
                formSelector: 'form',
                fullScreen: true,
                fullScreenClass: 'fullscreen',
                icon: null,
                touchSelector: 'a, .touch',
                popSelector: '.pop',
                preloadImages: false,
                slideSelector: 'body > * > ul li a',
                slideupSelector: '.slideup',
                startupScreen: null,
                statusBar: 'default', // other options: black-translucent, black
                submitSelector: '.submit',
                swapSelector: '.swap',
                useAnimations: true,
                useFastTouch: true // Experimental.
            };
            jQTSettings = $.extend({}, defaults, options);
            if (jQTSettings.fullScreen) {
                hairextensions += '<meta name="apple-mobile-web-app-capable" content="yes" />';
                if (jQTSettings.statusBar) {
                    hairextensions += '<meta name="apple-mobile-web-app-status-bar-style" content="' + jQTSettings.statusBar + '" />';
                }
            }
            if (hairextensions) $head.append(hairextensions);
            $(document).ready(function(){
                for (var i in extensions) 
                {
                    var fn = extensions[i];
                    if ($.isFunction(fn))
                    {
                        $.extend(publicObj, fn(publicObj));
                    }
                }

                touchSelectors.push(jQTSettings.touchSelector);
                touchSelectors.push(jQTSettings.backSelector);
                touchSelectors.push(jQTSettings.submitSelector);
                $(touchSelectors.join(', ')).css('-webkit-touch-callout', 'none');
                $body = $('body');
                if (jQTSettings.fullScreenClass && window.navigator.standalone == true) {
                    $body.addClass(jQTSettings.fullScreenClass + ' ' + jQTSettings.statusBar);
                }
                $body.bind('orientationchange', updateOrientation);
                    
                if ($('body > .current').length == 0) {
                    currentPage = $('body > *:first');
                } else {
                    currentPage = $('body > .current:first');
                    $('body > .current').removeClass('current');
                }
                $(currentPage).addClass('current');
                location.hash = $(currentPage).attr('id');
                scrollTo(0, 0);
            });
        }
        function updateOrientation() {
            orientation = window.innerWidth < window.innerHeight ? 'profile' : 'landscape';
            $body.removeClass('profile landscape').addClass(orientation).trigger('turn', {orientation: orientation});
            scrollTo(0, 0);
        }
        return publicObj;
    };
    $.jQTouch.prototype.extensions = [];
    $.jQTouch.addExtension = function(extension){
        $.jQTouch.prototype.extensions.push(extension);
    };
})(jQuery);