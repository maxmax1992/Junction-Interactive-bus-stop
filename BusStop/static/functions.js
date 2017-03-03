$(document).ready(function(){
  $('.upvote').click(function(){
    alert("Upvoted " + this.closest('div').id);
  });

  $('.downvote').click(function(){
    alert("Downvoted ");
  });
});
