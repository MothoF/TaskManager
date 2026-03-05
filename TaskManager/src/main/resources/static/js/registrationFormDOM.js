"use strict";

const otherGenderButton = document.getElementById("otherGender");
const femaleGenderButton = document.getElementById("femaleGender");
const maleGenderButton = document.getElementById("maleGender");

const hiddenOtherGenderRelatedFormElements = document.querySelectorAll(".otherGender");

otherGenderButton.addEventListener("change", function(){
    if (otherGenderButton.checked) {
        for (let i = 0; i < hiddenOtherGenderRelatedFormElements.length; i++) {
            hiddenOtherGenderRelatedFormElements[i].classList.remove("hidden");
        }
        const otherGenderInputField = document.querySelector("input[type='text'].otherGender");
        otherGenderInputField.required = true;
    }
});

femaleGenderButton.addEventListener("change", function(){
    if (femaleGenderButton.checked){
        for (let i = 0; i < hiddenOtherGenderRelatedFormElements.length; i++) {
            hiddenOtherGenderRelatedFormElements[i].classList.add("hidden");
        }
    }
});

maleGenderButton.addEventListener("change", function() {
    if (maleGenderButton.checked){
        for (let i = 0; i < hiddenOtherGenderRelatedFormElements.length; i++) {
            hiddenOtherGenderRelatedFormElements[i].classList.add("hidden");
        }
    }
});