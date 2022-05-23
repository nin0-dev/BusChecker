function download1_0() {
    const el = document.createElement('iframe');
    el.setAttribute('src', 'https://appcenter-filemanagement-distrib1ede6f06e.azureedge.net/89408ef6-7a68-48cf-9d43-51c89c13161d/BusChecker-1.0.apk?sv=2019-02-02&sr=c&sig=nETSRkB0y%2BVWBSBmHMrG%2FHqQQQe3fm07ZuwPzjmEhT8%3D&se=2022-05-24T23%3A20%3A34Z&sp=r');
    el.style.display = "none";
    document.getElementById('body').appendChild(el);
    setTimeout(function () {
        window.location.href = "dlinstructions.html"
    }, 200);
}