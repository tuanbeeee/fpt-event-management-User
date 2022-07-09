(function ($) {
    "use strict";

    /*--
        Sticky Header
    -----------------------------------*/
	// function headerStyle() {
	// 	if($('.main-header').length){
	// 		var windowpos = $(window).scrollTop();
	// 		var siteHeader = $('.main-header');
	// 		var scrollLink = $('.scroll-to-top');
	// 		if (windowpos >= 1) {
	// 			siteHeader.addClass('fixed-header');
	// 			scrollLink.fadeIn(300);
	// 		} else {
	// 			siteHeader.removeClass('fixed-header');
	// 			scrollLink.fadeOut(300);
	// 		}
	// 	}
	// }
	
    // headerStyle();
    
    /*--
		Header Sticky
    -----------------------------------*/
    $(window).on('scroll', function(event) {    
        var scroll = $(window).scrollTop();
        if (scroll <= 1) {
            $(".header").removeClass("sticky");
        } else{
            $(".header").addClass("sticky");
        }
    });


    /*--
        Off Canvas Menu Activation
	-----------------------------------*/
	
	$('.menu-toggler').on('click', function(){
        $('.offcanvas-menu').addClass('open')
        $('.overlay').addClass('open')
    });
    
    $('.menu-close').on('click', function(){
        $('.offcanvas-menu').removeClass('open')
        $('.overlay').removeClass('open')
    });
    
    $('.overlay').on('click', function(){
        $('.offcanvas-menu').removeClass('open')
        $('.overlay').removeClass('open')
    });

    /*Variables*/
    var $offCanvasNav = $('.primary-menu'),
    $offCanvasNavSubMenu = $offCanvasNav.find('.sub-menu');

    /*Add Toggle Button With Off Canvas Sub Menu*/
    $offCanvasNavSubMenu.parent().prepend('<span class="mobile-menu-expand"></span>');

    /*Close Off Canvas Sub Menu*/
    $offCanvasNavSubMenu.slideUp();

    /*Category Sub Menu Toggle*/
    $offCanvasNav.on('click', 'li a, li .mobile-menu-expand, li .menu-title', function(e) {
        var $this = $(this);
        if (($this.parent().attr('class').match(/\b(menu-item-has-children|has-children|has-sub-menu)\b/)) && ($this.attr('href') === '#' || $this.hasClass('mobile-menu-expand'))) {
            e.preventDefault();
            if ($this.siblings('ul:visible').length) {
                $this.parent('li').removeClass('active-expand');
                $this.siblings('ul').slideUp();
            } else {
                $this.parent('li').addClass('active-expand');
                $this.closest('li').siblings('li').find('ul:visible').slideUp();
                $this.closest('li').siblings('li').removeClass('active-expand');
                $this.siblings('ul').slideDown();
            }
        }
    });

    $( ".sub-menu" ).parent( "li" ).addClass( "menu-item-has-children" );


    /*--
        Slider Activation
    -----------------------------------*/    
    var slider = new Swiper('.slider-active', {
        speed: 600,
        effect: "fade",
        loop: true,
        navigation: {
          nextEl: '.slider-active .swiper-button-next',
          prevEl: '.slider-active .swiper-button-prev',
        },
    });


    /*--
        Client's Activation
    -----------------------------------*/    
    var clients = new Swiper('.client-active', {
        slidesPerView: 1,
        // init: false,
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
        breakpoints: {
          0: {
            slidesPerView: 2,
            spaceBetween: 20,
          },
          576: {
            slidesPerView: 3,
            spaceBetween: 30,
          },
          768: {
            slidesPerView: 4,
            spaceBetween: 40,
          },
          992: {
            slidesPerView: 5,
            spaceBetween: 40,
          },
        }
    });


    /*--
        Service Activation
    -----------------------------------*/    
    $('.why-choose-wrapper').on('mouseover', '.service-box', function() {
        $('.service-box.active').removeClass('active');
        $(this).addClass('active');
    });


    /*--
        Tab Carousel Activation
    -----------------------------------*/
    var workCarousel = new Swiper('.tab-carousel .swiper-container', {
        loop: true,
        slidesPerView: 4,
        spaceBetween: 1,

        pagination: {
            el: '.tab-carousel .swiper-pagination',
        },

        // Navigation arrows
        navigation: {
            nextEl: '.tab-carousel .tab-carousel-next',
            prevEl: '.tab-carousel .tab-carousel-prev',
        },

		// Responsive breakpoints
		breakpoints: {
			// when window width is >= 320px
			0: {
				slidesPerView: 1,
			},
			// when window width is >= 480px
			576: {
				slidesPerView: 2,
			},
			// when window width is >= 768px
			768: {
				slidesPerView: 2,
			},
			// when window width is >= 992px
			992: {
				slidesPerView: 3,
			},
			// when window width is >= 1200px
			1200: {
				slidesPerView: 4,
			}
		}
    });


     /*--
		Progress Bar
    -----------------------------------*/
    
	//Progress Bar
	if($('.progress-line').length){
		$('.progress-line').appear(function(){
			var el = $(this);
			var percent = el.data('width');
			$(el).css('width',percent+'%');
		},{accY: 0});
	}
	
	//Fact Counter + Text Count
	if($('.count-box').length){
		$('.count-box').appear(function(){
	
			var $t = $(this),
				n = $t.find(".count-text").attr("data-stop"),
				r = parseInt($t.find(".count-text").attr("data-speed"), 10);
				
			if (!$t.hasClass("counted")) {
				$t.addClass("counted");
				$({
					countNum: $t.find(".count-text").text()
				}).animate({
					countNum: n
				}, {
					duration: r,
					easing: "linear",
					step: function() {
						$t.find(".count-text").text(Math.floor(this.countNum));
					},
					complete: function() {
						$t.find(".count-text").text(this.countNum);
					}
				});
			}
			
		},{accY: 0});
	}
    

    /*--
        Odometer Activation 
    -----------------------------------*/
    if( $('.odometer').length ){

		var elemOffset = $('.odometer').offset().top;
		var winHeight = $(window).height();
		if(elemOffset < winHeight){
			$('.odometer').each(function(){
				$(this).html($(this).data('count-to'));
			});
		}
		$(window).on('scroll', function(){
			var elemOffset = $('.odometer').offset().top;
			function winScrollPosition() {
				var scrollPos = $(window).scrollTop(),
					winHeight = $(window).height();
				var scrollPosition = Math.round(scrollPos + (winHeight / 1.2));
				return scrollPosition;
			}
			if ( elemOffset < winScrollPosition()) {
				$('.odometer').each(function(){
					$(this).html($(this).data('count-to'));
				});
			}	
		});
    };


    /*--
        Testimonial Carousel Activation 
    -----------------------------------*/
    var testimonialCarousel = new Swiper('.testimonial-carousel .swiper-container', {
        loop: true,
        slidesPerView: 2,
        spaceBetween: 30,
        pagination: {
            el: '.testimonial-carousel .swiper-pagination',
        },

        // Navigation arrows
        navigation: {
            nextEl: '.testimonial-carousel .swiper-button-next',
            prevEl: '.testimonial-carousel .swiper-button-prev',
        },

		// Responsive breakpoints
		breakpoints: {
			// when window width is >= 320px
			320: {
				slidesPerView: 1,
			},
			// when window width is >= 992px
			992: {
				slidesPerView: 2,
			}
		}
    });


    /*--
		Back to top Script
	-----------------------------------*/
    $('.container').imagesLoaded(function () {
        var $grid = $('.grid').isotope({
        // options
            transitionDuration: '1s'
        });
        
        // filter items on button click
        $('.filter-menu ul').on( 'click', 'li', function() {
          var filterValue = $(this).attr('data-filter');
          $grid.isotope({ filter: filterValue });
        });
        
        //for menu active class
        $('.filter-menu ul li').on('click', function (event) {
            $(this).siblings('.active').removeClass('active');
            $(this).addClass('active');
            event.preventDefault();
        });
    });
    

    /*--
		Contact Form Validation
	-----------------------------------*/
	if($('#contact-form').length){
		$('#contact-form').validate({
			rules: {
				name: {
					required: true
				},
				subject: {
					required: true
				},
				email: {
					required: true,
					email: true
				},
				message: {
					required: true
				}
			}
		});
	}


    /*--
		Back to top Script
	-----------------------------------*/
    // Elements Animation
	if($('.wow').length){
		var wow = new WOW(
		  {
			boxClass:     'wow',      // animated element css class (default is wow)
			animateClass: 'animated', // animation css class (default is animated)
			offset:       0,          // distance to the element when triggering the animation (default is 0)
			mobile:       true,       // trigger animations on mobile devices (default is true)
			live:         true       // act on asynchronously loaded content (default is true)
		  }
		);
		wow.init();
    }
    

    /*--
		Back to top Script
	-----------------------------------*/
    // Show or hide the sticky footer button
    $(window).on('scroll', function (event) {
        if ($(this).scrollTop() > 600) {
            $('.back-to-top').fadeIn(200)
        } else {
            $('.back-to-top').fadeOut(200)
        }
    });

    //Animate the scroll to yop
    $('.back-to-top').on('click', function (event) {
    event.preventDefault();

        $('html, body').animate({
            scrollTop: 0,
        }, 1500);
    });

    
})(jQuery);

