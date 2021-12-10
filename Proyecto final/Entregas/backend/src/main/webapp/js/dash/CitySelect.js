class CitySelect{
    constructor(city){
        this.city = city;
        this.value = city;
        Object.seal(this);
    }

    render = (container) =>{
        let option = document.createElement("option");
        option.value = this.value;
        option.innerHTML += this.city;
        container.appendChild(option);
    }
}