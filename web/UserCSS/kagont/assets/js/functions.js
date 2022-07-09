(function ($) {
    "use strict";
    $(window).on('load', function () {
        $('.preloader').fadeOut(1000);
    })

    $(document).ready(function () {

        /*==== header Section Start here =====*/
        $("ul>li>.submenu").parent("li").addClass("menu-item-has-children");
        // drop down menu width overflow problem fix
        $('ul').parent('li').on('hover', function () {
            var menu = $(this).find("ul");
            var menupos = $(menu).offset();
            if (menupos.left + menu.width() > $(window).width()) {
                var newpos = -$(menu).width();
                menu.css({
                    left: newpos
                });
            }
        });
        $('.menu li a').on('click', function (e) {
            var element = $(this).parent('li');
            if (element.hasClass('open')) {
                element.removeClass('open');
                element.find('li').removeClass('open');
                element.find('ul').slideUp(300, "swing");
            } else {
                element.addClass('open');
                element.children('ul').slideDown(300, "swing");
                element.siblings('li').children('ul').slideUp(300, "swing");
                element.siblings('li').removeClass('open');
                element.siblings('li').find('li').removeClass('open');
                element.siblings('li').find('ul').slideUp(300, "swing");
            }
        });

        $('.header-bar').on('click', function () {
            $(this).toggleClass('active');
            $('.menu').toggleClass('active');
        })

        //Header
        var fixed_top = $("header");
        $(window).on('scroll', function () {
            if ($(this).scrollTop() > 300) {
                fixed_top.addClass("header-fixed fadeInUp");
            } else {
                fixed_top.removeClass("header-fixed fadeInUp");
            }
        });

        /*==== header Section End here =====*/






        // lightcase 
        $('a[data-rel^=lightcase]').lightcase();


        // scroll up start here
        $(function () {
            //Check to see if the window is top if not then display button
            $(window).scroll(function () {
                if ($(this).scrollTop() > 300) {
                    $('.scrollToTop').css({
                        'bottom': '2%',
                        'opacity': '1',
                        'transition': 'all .5s ease'
                    });
                } else {
                    $('.scrollToTop').css({
                        'bottom': '-30%',
                        'opacity': '0',
                        'transition': 'all .5s ease'
                    })
                }
            });
            //Click event to scroll to top
            $('.scrollToTop').on('click', function () {
                $('html, body').animate({
                    scrollTop: 0
                }, 500);
                return false;
            });
        });

        // product view mode change js
        $(function () {
            $('.product-view-mode').on('click', 'a', function (e) {
                e.preventDefault();
                var shopProductWrap = $('.shop-product-wrap');
                var viewMode = $(this).data('target');
                $('.product-view-mode a').removeClass('active');
                $(this).addClass('active');
                shopProductWrap.removeClass('grids lists').addClass(viewMode);
            });
        });

        // shop cart + - start here
        var CartPlusMinus = $('.cart-plus-minus');
        CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
        CartPlusMinus.append('<div class="inc qtybutton">+</div>');
        $(".qtybutton").on("click", function () {
            var $button = $(this);
            var oldValue = $button.parent().find("input").val();
            if ($button.text() === "+") {
                var newVal = parseFloat(oldValue) + 1;
            } else {
                // Don't allow decrementing below zero
                if (oldValue > 0) {
                    var newVal = parseFloat(oldValue) - 1;
                } else {
                    newVal = 1;
                }
            }
            $button.parent().find("input").val(newVal);
        });

        // shop single slider
        var galleryThumbs = new Swiper('.gallery-thumbs', {
            spaceBetween: 10,
            slidesPerView: 5,
            freeMode: true,
            watchSlidesVisibility: true,
            watchSlidesProgress: true,
            breakpoints: {
                768: {
                    slidesPerView: 4,
                },

                575: {
                    slidesPerView: 3,
                }
            }
        });
        var galleryTop = new Swiper('.gallery-top', {
            spaceBetween: 10,
            autoplay: {
                delay: 10000,
                disableOnInteraction: false,
            },
            navigation: {
                nextEl: '.shop-slider-next',
                prevEl: '.shop-slider-prev',
            },
            thumbs: {
                swiper: galleryThumbs
            },
            loop: true,
        });

        //Review Tabs
        $('ul.review-nav').on('click', 'li', function (e) {
            e.preventDefault();
            var reviewContent = $('.review-content');
            var viewRev = $(this).data('target');
            $('ul.review-nav li').removeClass('active');
            $(this).addClass('active');
            reviewContent.removeClass('review-content-show description-show').addClass(viewRev);
        });

        //shop dropdown categories
        $('.shop-menu li a').on('click', function (e) {
            var element = $(this).parent('li');
            if (element.hasClass('open')) {
                element.removeClass('open');
                element.find('li').removeClass('open');
                element.find('ul').slideUp(1000, "swing");
            } else {
                element.addClass('open');
                element.children('ul').slideDown(1000, "swing");
                element.siblings('li').children('ul').slideUp(1000, "swing");
                element.siblings('li').removeClass('open');
                element.siblings('li').find('li').removeClass('open');
                element.siblings('li').find('ul').slideUp(1000, "swing");
            }
        });
        //dropdown icon
        $("ul li ul").parent("li").addClass("menu-item-has-children");
        $(".shop-menu>li .shop-submenu").parent("li").children("a").addClass("dd-icon-down");


        //Isotope

        jQuery(window).on('load', function () {
            var $grid = $('.grid').isotope({
                itemSelector: '.grid-item',
                masonry: {
                    columnWidth: 0
                }
            })

            // filter items on button click
            $('.filter-button-group').on('click', '.filter-btn', function () {
                var filterValue = $(this).attr('data-filter');
                $grid.isotope({
                    filter: filterValue
                });
            });

            $('.filter-button-group').each(function (i, buttonGroup) {
                var $buttonGroup = $(buttonGroup);
                $buttonGroup.on('click', '.filter-btn', function () {
                    $buttonGroup.find('.is-checked').removeClass('is-checked');
                    $(this).addClass('is-checked');
                });
            });
        });

        //blog-slider

        var swiper = new Swiper('.post-thumb-container', {
            slidesPerView: 1,
            autoplay: {
                delay: 10000,
                disableOnInteraction: false,
            },
            navigation: {
                nextEl: '.thumb-next',
                prevEl: '.thumb-prev',
            },
            loop: true,
        });

        //Platinum Sponsor
        var swiper = new Swiper('.platinum-sponsor', {
            slidesPerView: 3,
            spaceBetween: 30,
            loop: true,
            autoplay: {
                delay: 3000,
                disableOnInteraction: true,
            },
            breakpoints: {
                575: {
                    spaceBetween: 20,
                    slidesPerView: 1,
                },
                767: {
                    spaceBetween: 20,
                    slidesPerView: 2,
                }
            }
        });

        //Gold Sponsor
        var swiper = new Swiper('.gold-sponsor', {
            slidesPerView: 4,
            slidesPerColumn: 2,
            spaceBetween: 40,
            loop: true,
            autoplay: {
                delay: 4000,
                disableOnInteraction: true,
            },
            breakpoints: {
                575: {
                    spaceBetween: 20,
                    slidesPerView: 1,
                },
                767: {
                    spaceBetween: 20,
                    slidesPerView: 2,
                }
            }
        });

        //Silver Sponsor
        var swiper = new Swiper('.silver-sponsor', {
            slidesPerView: 5,
            spaceBetween: 40,
            loop: true,
            autoplay: {
                delay: 5000,
                disableOnInteraction: true,
            },
            breakpoints: {
                575: {
                    spaceBetween: 20,
                    slidesPerView: 1,
                },
                767: {
                    spaceBetween: 20,
                    slidesPerView: 2,
                }
            }
        });

    });
    //progressbar
    window.addEventListener('DOMContentLoaded', function () {
        const circle = new CircularProgressBar('pie');
        setInterval(() => {
            const options = {
                index: 0,
                percent: Math.floor((Math.random() * 100) + 1)
            }
            circle.animationTo(options);
        }, 2000);

    });


    //Countdown js initialization
    document.addEventListener('readystatechange', event => {
        if (event.target.readyState === "complete") {
            var clockdiv = document.getElementsByClassName("count-down");
            var countDownDate = new Array();
            for (var i = 0; i < clockdiv.length; i++) {
                countDownDate[i] = new Array();
                countDownDate[i]['el'] = clockdiv[i];
                countDownDate[i]['time'] = new Date(clockdiv[i].getAttribute('data-date')).getTime();
                countDownDate[i]['days'] = 0;
                countDownDate[i]['hours'] = 0;
                countDownDate[i]['seconds'] = 0;
                countDownDate[i]['minutes'] = 0;
            }

            var countdownfunction = setInterval(function () {
                for (var i = 0; i < countDownDate.length; i++) {
                    var now = new Date().getTime();
                    var distance = countDownDate[i]['time'] - now;
                    countDownDate[i]['days'] = Math.floor(distance / (1000 * 60 * 60 * 24));
                    countDownDate[i]['hours'] = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    countDownDate[i]['minutes'] = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    countDownDate[i]['seconds'] = Math.floor((distance % (1000 * 60)) / 1000);

                    if (distance < 0) {
                        countDownDate[i]['el'].querySelector('.days').innerHTML = 0;
                        countDownDate[i]['el'].querySelector('.hours').innerHTML = 0;
                        countDownDate[i]['el'].querySelector('.minutes').innerHTML = 0;
                        countDownDate[i]['el'].querySelector('.seconds').innerHTML = 0;
                    } else {
                        countDownDate[i]['el'].querySelector('.days').innerHTML = countDownDate[i]['days'];
                        countDownDate[i]['el'].querySelector('.hours').innerHTML = countDownDate[i]['hours'];
                        countDownDate[i]['el'].querySelector('.minutes').innerHTML = countDownDate[i]['minutes'];
                        countDownDate[i]['el'].querySelector('.seconds').innerHTML = countDownDate[i]['seconds'];
                    }
                }
            }, 1000);
        }
    });
}(jQuery));


//Speaker item-2 hover js
$('.addClass').hover(function () {
    var element = $(this).parent('.speaker-inner');
    if (element.hasClass('show')) {
        element.removeClass('show');
        element.find('.speaker-inner').removeClass('show');
    } else {
        element.addClass('show');
    }

})

var app = angular.module('demoApp', ['ngAnimate']);
app.controller('demoController', function($scope){
	var opendd;
	var storedNewNotifications;
	var storedReadNotifications;
	var storedawaitingNotifications;
	var init = function(){
		storedNewNotifications = JSON.parse(localStorage.getItem('newNotifications'));
		storedReadNotifications = JSON.parse(localStorage.getItem('readNotifications'));
		storedawaitingNotifications = JSON.parse(localStorage.getItem('awaitingNotifications'));
		if(storedNewNotifications == null){
			$scope.newNotifications = [
				{
					user: pollingData.users[1],
					action: pollingData.actions[0],
					target: pollingData.actionTargets[2],
					timestamp: new Date()
				}
			];
		}
		else{
			$scope.newNotifications = storedNewNotifications;
		}
		if(storedReadNotifications == null){
			$scope.readNotifications = [
				{
					user: pollingData.users[2],
					action: pollingData.actions[1],
					target: pollingData.actionTargets[0],
					timestamp: new Date()
				}
			];
		}
		else{
			$scope.readNotifications = storedReadNotifications;
		}
		if(storedawaitingNotifications == null)
			$scope.awaitingNotifications = 1;
		else{
			$scope.awaitingNotifications = storedawaitingNotifications;
			if($scope.awaitingNotifications == 0)
				angular.element('#notifications-count').hide();
		}
		$scope.showNotifications = function($event){
			var targetdd = angular.element($event.target).closest('.dropdown-container').find('.dropdown-menu');
			opendd = targetdd;
		    if(targetdd.hasClass('fadeInDown')){
		    	hidedd(targetdd);
		    }
		    else{
		    	targetdd.css('display', 'block').removeClass('fadeOutUp').addClass('fadeInDown')
		    									.on('animationend webkitAnimationEnd oanimationend MSAnimationEnd', function(){
	  												angular.element(this).show();
	  											});
          targetdd.find('.dropdown-body')[0].scrollTop = 0;
		    	$scope.awaitingNotifications = 0;
		      	angular.element('#notifications-count').removeClass('fadeIn').addClass('fadeOut');
		    }
		};
		$scope.hideInfo = function(){
			angular.element('#demoInfo').addClass('zoomOut')
										.on('animationend webkitAnimationEnd oanimationend MSAnimationEnd', function(){
											angular.element(this).hide();
											angular.element('.instruction').addClass('zoomIn').show();
										});
		}
		//show notifications count if new notifications are received
		setInterval(function(){
			if($scope.awaitingNotifications > 0 && opendd == null && (angular.element('#notifications-count').css('opacity') == '0' || angular.element('#notifications-count').is(':hidden')))
    			angular.element('#notifications-count').removeClass('fadeOut').addClass('fadeIn').show();
		}, 400);
		dummyPolling();
	}

	//Hide dropdown function
	var hidedd = function(targetdd){
		targetdd.removeClass('fadeInDown').addClass('fadeOutUp')
										  .on('animationend webkitAnimationEnd oanimationend MSAnimationEnd', function(){
  												angular.element(this).hide();
  											});
    	opendd = null;
    	$scope.awaitingNotifications = 0;
    	angular.forEach($scope.newNotifications, function(notification){
    		$scope.readNotifications.push(notification);
    	});
    	$scope.newNotifications = [];
    	localStorage.setItem('readNotifications', JSON.stringify($scope.readNotifications));
    	localStorage.setItem('newNotifications', JSON.stringify($scope.newNotifications));
		localStorage.setItem('awaitingNotifications', JSON.stringify($scope.awaitingNotifications));
    	if($scope.awaitingNotifications > 0)
    		angular.element('#notifications-count').removeClass('fadeOut').addClass('fadeIn');
	}

	//New notification is created by selecting random user, action and targets from this object
	var pollingData = {
	    users : [
		    {
		        name: "Fauzan Khan",
		        imageUrl: "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAANfAAAAJDE1MzNiYjM1LWVjYzUtNDcwZi1hMmExLTQ5ZDVjYzViMDkzYQ.jpg"
		    },
		    {
		        name: "Keanu Reeves",
		        imageUrl: "http://www.latimes.com/includes/projects/hollywood/portraits/keanu_reeves.jpg"
		    },
		    {
		        name: "Natalie Portman",
		        imageUrl: "https://imagemoved.files.wordpress.com/2011/07/no-strings-attached-natalie-portman-19128381-850-1280.jpg"
		    }
	    ],
	    actions: ["upvoted", "promoted", "shared"],
  	    actionTargets: ["your answer", "your post", "your question"]
	};

	//generates a random number between 0 and 2 to select random polling data
	var getRandomNumber = function(){
	    return Math.floor(Math.random() * 3);
	};

	//creates and returns a new notification
	var getNewNotification = function(){
		var userIndex = getRandomNumber();
		var actionIndex = getRandomNumber();
		var actionTargetIndex = getRandomNumber();
		var newNotification = {
			user: pollingData.users[userIndex],
			action: pollingData.actions[actionIndex],
			target: pollingData.actionTargets[actionTargetIndex],
			timestamp: new Date()
		}
		return newNotification;
	};

	//This function calls itslef after random interval
	var dummyPolling = function(){
		var randomInterval = 2*Math.round(Math.random() * (3000 - 500)) + 1000;
		setTimeout(function() {
			$scope.$apply(function(){
				$scope.newNotifications.push(getNewNotification());
				$scope.awaitingNotifications++;
				localStorage.setItem('newNotifications', JSON.stringify($scope.newNotifications));
				localStorage.setItem('awaitingNotifications', JSON.stringify($scope.awaitingNotifications));
			});
			console.log("dummy poll called after "+randomInterval+"ms");
            dummyPolling();  
    	}, randomInterval);
	}

	window.onclick = function(event){
		var clickedElement = angular.element(event.target);
		var clickedDdTrigger = clickedElement.closest('.dd-trigger').length;
		var clickedDdContainer = clickedElement.closest('.dropdown-menu').length;
		if(opendd != null && clickedDdTrigger == 0 && clickedDdContainer == 0){
			hidedd(opendd);
		}
	}
  
  window.onbeforeunload = function(e) {
	  if(opendd != null){
      console.log('closingdd');
      hidedd(opendd); 
    }
	};

	init();
})