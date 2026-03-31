const menuItems = document.querySelectorAll(".menuItem");
console.log(menuItems);
for (let i = 0; i < menuItems.length; i ++){
    console.log(menuItems[i]);
    item = menuItems[i];
    if (item.href === window.location.href){
        item.classList.add("active");
    } else if (item.href !== window.location.href && item.classList.contains("active")){
        item.classList.remove("active");
    }
}