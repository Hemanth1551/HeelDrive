const ImgShow = document.getElementById("shoe");
const Text = document.getElementById("top-matter-text-one")
const Text2 = document.getElementById("top-matter-text-two")

const isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);

let boxes = document.querySelectorAll('.box');

function toggleMenu(x) {
    x.classList.toggle('change');
    document.getElementById('mask-cover').classList.toggle('show-mask');
}

window.addEventListener('scroll', Imgs);
window.addEventListener('scroll', texts);
window.addEventListener('scroll', boxs);


function Imgs() {
    let endLine = window.innerHeight * 0.7;

    // Get the top position of the image relative to the viewport
    let ImgHeight = ImgShow.getBoundingClientRect().top;


    if (ImgHeight < endLine) {
        ImgShow.classList.add('show');
        
    } else {
        ImgShow.classList.remove('show');
    }
}

Imgs();


// Adjustable, Stable, flexible, hardness

function texts(){
    let endLine1 = window.innerHeight * 0.7;

    // Get the top position of the image relative to the viewport
    let texth = Text.getBoundingClientRect().top;
    if (isMobile) {
        console.log("This is a mobile device.");
        Text.classList.add('tt');
        Text2.classList.add('tt');
      } else {
        console.log("This is not a mobile device.");
        if (texth < endLine1) {
            Text.classList.add('tt');
            Text2.classList.add('tt');
        } else {
            Text.classList.remove('tt');
            Text2.classList.remove('tt');
        }
      }

}

texts();





function boxs() {
    // Calculate the vertical threshold based on window height
    let endLine1 = window.innerHeight * 0.5;

    // Select all elements with the class "box"
    let box1 = document.getElementById('box1');
    let box2 = document.getElementById('box2');
    let box3 = document.getElementById('box3');

    // Loop through each box element
        // Get the top position of the current box relative to the viewport
        let boxh = box1.getBoundingClientRect().top;

        // Check if the box is within the end line
        if(isMobile){
             box1.classList.add('ttt');
            box2.classList.add('ttt');
            box3.classList.add('ttt');
        }
        else{
            if (boxh < endLine1) {
            box1.classList.add('ttt');
            box2.classList.add('ttt');
            box3.classList.add('ttt');

        } else {
            box1.classList.remove('ttt');
            box2.classList.remove('ttt');
            box3.classList.remove('ttt');
        }

        }
}

boxs();


// if (boxh < endLine1) {
//             box1.classList.add('ttt');
//             box2.classList.add('ttt');
//             box3.classList.add('ttt');

//         } else {
//             box1.classList.remove('ttt');
//             box2.classList.remove('ttt');
//             box3.classList.remove('ttt');
//         }


// // const hh = document.querySelectorAll('.button');

// console.log(window.innerHeight)