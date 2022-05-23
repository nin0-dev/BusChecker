function download1_0()
{
    const el = document.createElement('iframe');
    el.setAttribute('src', 'https://appcenter-filemanagement-distrib3ede6f06e.azureedge.net/8b19157a-3eba-44a1-a3fe-255afaa16acf/BusChecker.apk?sv=2019-02-02&sr=c&sig=6ITHMnyQsHjWCIvLwm%2FuK4%2FOwl3Nvpm8hOgNqDbGmzU%3D&se=2022-05-23T22%3A44%3A09Z&sp=r');
    el.style.display = "none";
    document.getElementById('body').appendChild(el);
    window.location.href = "dlinstructions.html"
}