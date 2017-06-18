$(document).ready(

  function(){

    // Comprobar si estamos, al menos, 100 px por debajo de la posición top
    // para mostrar o esconder el botón
    $(window).scroll(function(){

      if ( $(this).scrollTop() > 100 ) {

        $('.scroll-to-top').fadeIn();

      } else {

        $('.scroll-to-top').fadeOut();

      }

    });

    // al hacer click, animar el scroll hacia arriba
    $('.scroll-to-top').click( function( e ) {

      e.preventDefault();
      $('html, body').animate( {scrollTop : 0}, 800 );

    });

  });