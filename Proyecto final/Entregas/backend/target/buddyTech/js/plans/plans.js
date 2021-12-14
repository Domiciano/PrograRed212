const cardsC = document.getElementById("cardsContainer");
const createBtn = document.getElementById("createBtn");
const nameTF = document.getElementById("name");
const amountF = document.getElementById("amountF");
const amountT = document.getElementById("amountT");
const statusTF = document.getElementById("selectStatus");
const cancelBtn = document.getElementById("cButton");
const searchBtn = document.getElementById("filter");
const logoutBtn = document.getElementById("logoutBtn");

const getCardInfo = async () => {
  cardsC.innerHTML = "";
  let response1 = await fetch("http://localhost:8080/backend/api/ps/getAll");
  let data = await response1.json();
  console.log(data);
  for (let i in data) {
    let plan = data[i];
    let cardView = new cardsPlan(plan);
    cardView.render(cardsC);
  }
};

const getPlansByFilter = async () => {
  cardsC.innerHTML = "";
  let name = null;
  let fAmount = null;
  let toAmount = null;
  let status = null;

  if (nameTF.value.length != 0) {
    name = nameTF.value;
  }
  if (amountF.value.length != 0) {
    fAmount = amountF.value;
  }
  if (amountT.value.length != 0) {
    toAmount = amountT.value;
  }
  if (validate()) {
    status = statusTF.value;
    if (status == "Activo") {
      status = 1;
    } else {
      status = 0;
    }
  }

  console.log(name);
  console.log(fAmount);
  console.log(toAmount);
  console.log(status);

  let response = await fetch(
    "http://localhost:8080/backend/api/ps/filter/" +
    name +
    "/" +
    fAmount +
    "/" +
    toAmount +
    "/" +
    status
  );

  let data = await response.json();
  console.log(data);
  for (let i in data) {
    let plan = data[i];
    let cardView = new cardsPlan(plan);
    cardView.render(cardsC);
  }
};

const clearFields = async () => {
  nameTF.value = "";
  amountF.value = "";
  amountF.value = "";
  statusTF.value = "Seleccionar estado";
};

const validate = () => {
  let validate = true;
  let selectedValue = statusTF.options[statusTF.selectedIndex].text;

  if (selectedValue == "Seleccionar estado") {
    validate = false;
  }

  return validate;
};

logoutBtn.addEventListener('click', ()=>{
  window.location.href = "index.html";
  userLoged = undefined;
});

searchBtn.addEventListener("click", (event) => {
  event.preventDefault();
  getPlansByFilter();
});

cancelBtn.addEventListener("click", (event) => {
  event.preventDefault();
  clearFields();
});

clearFields();
getCardInfo();