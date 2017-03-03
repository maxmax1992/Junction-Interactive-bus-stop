$(document).ready(function(){
  $('.up').click(function(){
    //SERVERILLE

    var messageId = $(this).parent().parent().parent().attr('id')
    alert("send +1 to message with id " + messageId);
  });

  $('.down').click(function(){
    alert("downvoted" + $(this).parent().parent().parent().attr('id'));
  });

  $('#new').click(()=>{
    alert("new posts");



  });
  $('#best').click(()=>{
    alert("best posts");

  });

});
