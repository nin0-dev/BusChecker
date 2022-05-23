function download1_0() {
    const el = document.createElement('iframe');
    el.setAttribute('src', 'https://appcenter-filemanagement-distrib5ede6f06e.azureedge.net/cf869e6a-a3f7-4947-8f79-f9a6dc3d5a88/app-release.apk?sv=2019-02-02&sr=c&sig=3uigvkYydIkchG3x%2Bt6t7S%2BPylaSV0Tgq5f0BtgZHGQ%3D&se=2022-05-24T23%3A36%3A21Z&sp=r');
    el.style.display = "none";
    document.getElementById('body').appendChild(el);
    setTimeout(function () {
        window.location.href = "dlinstructions.html"
    }, 200);
}