console.log('Hello World Script Loaded');

(function(){
  function reqListener () {
    console.log('Our API responded: ' + this.responseText);
  }

  var oReq = new XMLHttpRequest();
  oReq.addEventListener('load', reqListener);
  oReq.open('GET', 'api/hello?name=Kotlin Coder');
  oReq.send();
})();