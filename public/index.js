var bMobile =   // will be true if running on a mobile device
  navigator.userAgent.indexOf( "Mobile" ) !== -1 || 
  navigator.userAgent.indexOf( "iPhone" ) !== -1 || 
  navigator.userAgent.indexOf( "Android" ) !== -1 || 
  navigator.userAgent.indexOf( "Windows Phone" ) !== -1 ;
if(bMobile) {
    // On mobile
    document.getElementById("pc-navbar").style.display = "none"
}
else {
    // On PC
    document.getElementById("mobile-navbar").style.display = "none"
}