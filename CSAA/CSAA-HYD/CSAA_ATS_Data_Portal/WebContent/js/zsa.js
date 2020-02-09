/*$Id:$*/
var t=0;
var si;
//cookie 
function sc(n,v,e,p,d) { document.cookie= n+"="+escape(v)+";expires="+e.toGMTString()+";path="+p+";domain="+d+";"; }
function gc(n,s) {
   var rc= new RegExp(n+"=[^;]*").exec(document.cookie);
   if(rc){
       if(s){
           return unescape(rc[0].split("=").pop());
       }
       else{
           return unescape(rc);
       }
    }
   else{
       return null;
   }
}
// get GA data
function checkGA(){
    if(t>100){
        clearInterval(si);
    }
    if(window._gaq){
        clearInterval(si);
	_gaq.push(function(){ getGA();});
    }
    else{
        t++;
        return;
    }

}


function getGA(){
   var pu = gc('zsa_vid',true);// check already user visit our site // No I18N
    if(!pu){
       var _ua = gc('__utma',true); // No I18N
       var d=document;
       var l=location;
       var zsa_utm={};
       // get referrer domain
       var r=(l.search.indexOf('rdn')!=-1)?l.href:d.referrer;
       zsa_utm.qs = encodeURIComponent(l.search.substr(1));//escape(r.replace(/(ht?tps?\:\/\/)?([^\?]*)(.*)/g,"$3")); // get query string  
       if(r!=""){
           var QA = ["q","p","search_word","query","terms","rdata","wd","text","gt","rdn","sq"]; // q= for Google, p= for Yahoo like this is get ga.js // No I18N
           rdn = r.replace(/(ht?tps?\:\/\/)?([^\/]*)\/?(.*)/g,"$2"); // get domain name 
           if(rdn!="www.zoho.com"){
               zsa_utm.rdn=rdn;// No I18N
           }
           var qs = r.substr(r.indexOf('?')+1);
           var qsa = qs.split('&');
           for (var i=0;i<qsa.length;i++) {
               var qsfv = qsa[i].split('=');
               if (qsfv.length == 1) continue;
               for(var k=0;k<QA.length;k++){
                   if(QA[k] == qsfv[0]) { // q= for Google, p= for Yahoo
                       if(qsfv[0]=="rdn"){
                           zsa_utm.rdn=unescape(qsfv[1]);
                       }
                       else{
                           zsa_utm.sq=unescape(qsfv[1]);
                       }
                   }
               }
           }
       }
	// get ga cookie info
       var uid = (_ua?_ua.split(".").splice(1,2).join("."):(Math.random()*10000000000+"-"+new Date().getTime())); // uuid generate from ga utma cookie
       sc('zsa_vid',uid,new Date(new Date().getTime()+(86400000*730)),"/","zoho.com"); // No I18N
      
      // only send zsa_qs's query string
      var zsa_qs=["rdn","sq","qs"];
      var zs_qs="";
      for(var i in zsa_qs){
          if(zsa_utm[zsa_qs[i]]){
              zs_qs+="&"+zsa_qs[i]+"="+zsa_utm[zsa_qs[i]];
          }
      }

(function(){var zsa = document.createElement('script'); zsa.type="text/javascript"; zsa.src='//sites.zoho.com/zsaPush?'+zs_qs;var h=document.getElementsByTagName('head')[0];h.appendChild(zsa);})();
   }
}
si = setInterval(function(){checkGA();},1000);