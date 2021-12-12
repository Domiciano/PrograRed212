const nameTF = document.getElementById("sName");
const time = document.getElementById("sTime");
const statusTF = document.getElementById("sStatus");
const amount = document.getElementById("sAmount");
const cancelBtn = document.getElementById("cBtn");
const createBtn = document.getElementById("creBtn");

const createPlan = async ()=>{
   let plan = {
       name:nameTF.value,
       time:time.value,
       status:statusTF.value,
       amount:amount.value
   };
   console.log(plan);
   let json = JSON.stringify(plan);
   let response = await fetch("http://localhost:8080/backend/api/ps/",
        {
            method: "POST",
            headers: {
                "content-type":"aplication/json",
            },
            body: json
        } 
   )
    if(response.ok){
        swal("Datos actualizados", "creaste un nuevo plan","success");
    }  
}

const clearFields = async()=>{
    nameTF.value="";
    time.value="";
    statusTF.value="";
    amount.value="";
}

createBtn.addEventListener("click", (event)=>{
    event.preventDefault();
    createPlan();
    clearFields();
});

cancelBtn.addEventListener("click", (event)=>{
    event.preventDefault();
    clearFields();
});