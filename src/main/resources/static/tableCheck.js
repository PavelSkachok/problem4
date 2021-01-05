function check()
{let incl=document.querySelector('#selAll').checked==false;
    let check = document.querySelectorAll('input[type="checkbox"]');
    check.forEach(v => v.checked=!incl)
}