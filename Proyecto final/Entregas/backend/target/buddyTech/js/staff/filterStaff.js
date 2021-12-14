var cardsContainer = document.getElementById("cardsContainer");
var id = document.getElementById("id");
var userN = document.getElementById("nameC");
var lastname = document.getElementById("lastname");
var age = document.getElementById("age");
var selectPlan = document.getElementById("selectPlan");
var selectStatus = document.getElementById("selectStatus");
var filterbtn = document.getElementById("filter");
var cancelbtn = document.getElementById("cButton");
var venuesD;

const filtering = async () => {
  cardsContainer.innerHTML = "";
  let natId = null;
  let name = null;
  let lastN = null;
  let ageC = null;
  let planChoosen = null;
  let status = null;
  if (id.value.length != 0) {
    natId = id.value;
  }
  if (userN.value.length != 0) {
    name = userN.value;
  }
  if (lastname.value.length != 0) {
    lastN = lastname.value;
  }
  if (age.value.length != 0) {
    ageC = age.value;
  }
  if (validatePlan()) {
    planChoosen = selectPlan.options[selectPlan.selectedIndex].text;
  }
  if (validateStatus()) {
    status = selectStatus.options[selectStatus.selectedIndex].text;
  }
  let response1 = await fetch(
    "http://localhost:8080/backend/api/cls/filter/" +
      natId +
      "/" +
      name +
      "/" +
      lastN +
      "/" +
      ageC +
      "/" +
      planChoosen +
      "/" +
      status
  );
  let info = await response1.json();
  for (let i in info) {
    let obtained = info[i];
    let response2 = await fetch(
      "http://localhost:8080/backend/api/cls/cardInfo/" + obtained.client.natId
    );
    let card = await response2.json();
    let cardView = new Card(card[0]);
    cardView.filterCard(cardsContainer);
  }
};

const getPlans = async () => {
  let html = `<option selected disabled selected hidden>Seleccionar plan</option>`;
  let plans = await fetch("http://localhost:8080/backend/api/ps/getactive");
  statusReady = await plans.json();

  for (let i in statusReady) {
    let incomingName = statusReady[i].name;
    html += `<option value="${i}">${incomingName}</option>`;
  }
  selectPlan.innerHTML = html;
};

const getStatus = async () => {
  let html = `<option selected disabled selected hidden>Seleccionar estado</option>`;
  let statusList = await fetch(
    "http://localhost:8080/backend/api/css/clientsState"
  );
  statusReady = await statusList.json();

  for (let i in statusReady) {
    let incomingName = statusReady[i].status;
    html += `<option value="${i}">${incomingName}</option>`;
  }
  selectStatus.innerHTML = html;
};

const validatePlan = () => {
  let validate = true;
  let selectedValuePlan = selectPlan.options[selectPlan.selectedIndex].text;

  if (selectedValuePlan == "Seleccionar plan") {
    validate = false;
  }

  return validate;
};

const validateStatus = () => {
  let validate = true;
  let selectedValueStatus =
    selectStatus.options[selectStatus.selectedIndex].text;

  if (selectedValueStatus == "Seleccionar estado") {
    validate = false;
  }

  return validate;
};

const clearFields = async () => {
  userN.value = "";
  lastname.value = "";
  id.value = "";
  age.value = "";

  selectPlan.innerHTML =
    "<option selected disabled selected hidden>Seleccionar Plan</option>";
  selectStatus.innerHTML =
    "<option selected disabled selected hidden>Seleccionar estado</option>";
};

filterbtn.addEventListener("click", (event) => {
  event.preventDefault();
  filtering();
  getPlans();
  getStatus();
  clearFields();
});

cancelbtn.addEventListener("click", (event) => {
  event.preventDefault();
  clearFields();
});

logoutBtn.addEventListener('click', ()=>{
  window.location.href = "index.html";
  userLoged = undefined;
});

getPlans();
getStatus();
clearFields();
