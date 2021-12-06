const loginBtn = document.getElementById("loginBtn");
const userIdTF = document.getElementById("userIdTF");
const oneBtn = document.getElementById("oneBtn");
const twoBtn = document.getElementById("twoBtn"); 
const threeBtn = document.getElementById("threeBtn");
const fourBtn = document.getElementById("fourBtn");
const fiveBtn = document.getElementById("fiveBtn");
const sixBtn = document.getElementById("sixBtn");
const sevenBtn = document.getElementById("sevenBtn");
const eightBtn = document.getElementById("eightBtn");
const nineBtn = document.getElementById("nineBtn");
const zeroBtn = document.getElementById("zeroBtn");
const clearBtn = document.getElementById("clearBtn");
const myModal = new bootstrap.Modal(document.getElementById('loginModal'));
const modalBody = document.getElementById("loginModalBody");


const login = async ()=>{
    modalBody.innerHTML = "";
    //Al darle login que me mande a tal página, se debe guardar
    //el cliente en localstorage
    if(userIdTF.value.length !== 0){
        let searching = await fetch("http://localhost:8080/backend/api/cls/searchclient/"+userIdTF.value); 

        let data = await searching.json();
        console.log(data);
        if(data.length === 0){




            myModal.show();

        } else {

                let clientFound = data[0];
                //Revisar que esté afuera
                if(clientFound.statusID === 3){

                    let validateMemberships = await fetch("http://localhost:8080/backend/api/ms/searchmembership/"+clientFound.membershipID);
                    let usermemship = await validateMemberships.json();
                    console.log(usermemship);
                    let memberEndDate;
                    for(let i in usermemship){
                        memberEndDate = usermemship[i].endDate;
                        actualMembership = usermemship[i];    
                    }
                    
                    if(evaluateDateAccess(memberEndDate)){
                        //Poner el cliente con el statusID de 2 ya que está adentro en ese momento (se hace despues de verificar el estado de la membresia)
                        let xhr = new XMLHttpRequest();
                        xhr.addEventListener('readystatechange', ()=>{
                            if(xhr.readyState == 4){
                                var response = JSON.parse(xhr.responseText);
                                console.log(response.message);
                                if(response.message == 'Estado del cliente ha sido cambiado'){
                                    let days = daysLeft(memberEndDate);
                                    html = `<h5> ${clientFound.name} ${clientFound.lastname} </h5>
                                    <p class="mb-0"> Plan Type</p>
                                    <p class="mb-0"> Days Left: ${days}</p>
                                    <small> Success</small>`
                                    modalBody.innerHTML = html;
                                    myModal.show();
                                } else {
                                    alert('No se pudo cambiar el estado del cliente');
                                }
                            }
                        });
                    xhr.open('PUT', 'http://localhost:8080/backend/api/cls/editclientstatusbyid/'+clientFound.natId+'/'+2);
                    xhr.send();
                    } else {
                        alert("La membresía ha expirado, porfavor contacte a un staff para renovarla");

                    }

                }  else if(clientFound.statusID === 2){
                    alert("La membresia del cliente ya está en uso");

                } else if(clientFound.statusID === 1){
                    alert("La persona se encuentra bloqueada en su totalidad");

                }     
            }
       // location.href = "dashboard.html";
    } else {
        alert("Ingrese un documento válido");
    }

};

loginBtn.addEventListener("click", (event) =>{
    event.preventDefault();
    login();
});


//Al método le ingresa una fecha del atributo de endDate del cliente y retorna los días remanentes a vencerse
const daysLeft = (memEndDate)=>{

    let userEndDate = new Date(memEndDate);
    let today = new Date();

    let difference = Math.abs(userEndDate-today);
    let days = parseInt((difference/(1000 * 3600 * 24)));
    console.log("days left: "+days);

    return days;
}

//Al metodo le ingresa una fecha de expiración dada por el cliente y saca un boolean notificando si puede acceder o no
const evaluateDateAccess = (memEndDate) =>{
    let userEndDate = new Date(memEndDate);
    let userDate = userEndDate.getFullYear()+'/'+(userEndDate.getMonth()+1)+'/'+userEndDate.getDate();
    let today = new Date();
    let todayDate = today.getFullYear()+'/'+(today.getMonth()+1)+'/'+today.getDate();
    
return (userDate > todayDate);

}
