const nameTF = document.getElementById("sName");
const timeTF = document.getElementById("sTime");
const statusSelector = document.getElementById("sStatus");
const amountTF = document.getElementById("sAmount");
const cancelBtn = document.getElementById("cBtn");
const createBtn = document.getElementById("creBtn");


const validateSelector = () => {
    let validate = true;
    let selectedValuePlan = statusSelector.options[statusSelector.selectedIndex].text;

    if (selectedValuePlan == "Activo o inactivo") {
        validate = false;
    }
    return validate;
}

const createPlan = async () => {

    if ((nameTF.value.length !== 0) && (timeTF.value.length !== 0) && (validateSelector()) && (amountTF.value.length !== 0)) {

        let plan = {
            name: nameTF.value,
            time: timeTF.value,
            status: statusSelector.value,
            amount: amountTF.value
        };
        console.log(plan);
        let json = JSON.stringify(plan);
        let response = await fetch("http://localhost:8080/backend/api/ps/insert",
            {
                method: "POST",
                headers: {
                    "content-type": "aplication/json",
                },
                body: json
            }
        )
        if (response.ok) {
            alert("Plan creado satisfactoriamente");
        }
    } else {
        alert("Complete todos los campos")
    }


}

const clearFields = async () => {
    nameTF.value = "";
    timeTF.value = "";
    amountTF.value = "";
    statusSelector.value = "Activo o inactivo";
}

createBtn.addEventListener("click", (event) => {
    event.preventDefault();
    createPlan();
    clearFields();
});

cancelBtn.addEventListener("click", (event) => {
    event.preventDefault();
    clearFields();
});