/*CONSTANTS*/
const PIZZA_API_URL = "http://localhost:8080/api/v1/pizzas";
const INGREDIENTS_API_URL = "http://localhost:8080/api/v1/ingredients";
const pizzaForm = document.getElementById("pizza-form");
const creationMessage = document.getElementById("creation-message");

/*API*/
//OTTENGO INGREDIENTI
const getIngredients = async () => {
  try {
    const response = await axios.get(INGREDIENTS_API_URL);
    console.log(response);
    return response.data;
  } catch (error) {
    console.log(error);
    //se ho errore, restituisco array vuoto
    return [];
  }
};

/*DOM MANIPULATION*/
//CREA CHECKBOX CON INGREDIENTI
const ingredientsCheckboxes = (ingredients) => {
  const checkboxContainer = document.getElementById("ingredients-checkboxes");
  checkboxContainer.innerHTML = `<label class="form-label">Ingredients</label>`;

  ingredients.forEach((ingredient) => {
    const checkboxDiv = document.createElement("div");
    checkboxDiv.className = "form-check";
    checkboxDiv.innerHTML = `
        <input class="form-check-input" type="checkbox" value="${ingredient.id}" id="ingredient-${ingredient.id}">
        <label class="form-check-label" for="ingredient-${ingredient.id}">${ingredient.name}</label>
      `;
    checkboxContainer.appendChild(checkboxDiv);
  });
};

//carica ingredienti e crea le checkbox quando la pagina è pronta
document.addEventListener("DOMContentLoaded", async () => {
  const ingredients = await getIngredients();
  ingredientsCheckboxes(ingredients);
});

//CREA PIZZA
const createPizza = async (event) => {
  event.preventDefault();

  const name = document.getElementById("name").value;
  const description = document.getElementById("description").value;
  const price = document.getElementById("price").value;
  const urlPhoto = document.getElementById("url-photo").value;

  //ottengo array di oggetti Ingredient selezionati dall' utente
  const selectedIngredients = Array.from(
    document.querySelectorAll(".form-check-input:checked")
  ).map((input) => ({
    id: parseInt(input.value),
    name: input.nextElementSibling.textContent,
  }));

  const pizzaData = {
    name,
    description,
    urlPhoto,
    price: parseFloat(price),
    ingredients: selectedIngredients,
  };

  try {
    const response = await axios.post(PIZZA_API_URL, pizzaData);
    console.log(response);

    //messaggio di creazione avvenuta
    creationMessage.innerHTML = `<div class= "alert alert-success mt-4 w-100"> Pizza ${pizzaData.name} was added to the menu! </div>`;
  } catch (error) {
    console.log(error);

    //messaggio di creazione non avvenuta
    creationMessage.innerHTML = `<div class= "alert alert-danger mt-4 w-100"> Sorry, it was not possible to add Pizza ${pizzaData.name} to the menu :( </div>`;
  }
};

//gestione submit/salva nuova pizza
pizzaForm.addEventListener("submit", createPizza);
