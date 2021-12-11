class CitySelect{
    constructor(city, selectCity){
        this.city = city;
        this.value = city;
        this.selectCity = selectCity;
        Object.seal(this);
    }

    render = (container) =>{
        let option = document.createElement("option");
        option.value = this.value;
        option.innerHTML += this.city;
        if(this.city === this.selectCity){
            option.selected = true;
        }
        container.appendChild(option);
    }
}