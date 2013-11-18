!function ($) {

  $(function(){

    var $window = $(window)

    // make code pretty
    window.prettyPrint && prettyPrint()

    // popover demo
    $("a[data-toggle=popover]")
      .popover()
      .click(function(e) {
        e.preventDefault()
      })
  })
}(window.jQuery)
