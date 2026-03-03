const otherGenderButton = document.getElementById("otherGender");

otherGenderButton.addEventListener("change", function(){
    if (otherGenderButton.checked) {
        const hiddenOtherGenderRelatedFormElements = document.querySelectorAll(".otherGender");
        for (let i = 0; i < hiddenGenderRelatedFormElements.length; i++) {
            hiddenOtherGenderRelatedFormElements[i].classList.remove("hidden");
        }
    }
    else {
        const visibleOtherGenderRelatedElements = document.querySelectorAll(".otherGender");
        for (let i = 0; i < visibleOtherGenderRelatedElements.length; i++) {
            visibleOtherGenderRelatedElements[i].classList.add("hidden");
        }
    }
});