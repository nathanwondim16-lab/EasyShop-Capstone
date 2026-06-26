
function showLoginForm()
{
    templateBuilder.build('login-form', {}, 'login');
}

function hideModalForm()
{
    templateBuilder.clear('login');
}

function login()
{
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    userService.login(username, password);
    hideModalForm()
}

function showImageDetailForm(product, imageUrl)
{
    const imageDetail = {
        name: product,
        imageUrl: imageUrl
    };

    templateBuilder.build('image-detail',imageDetail,'login')
}

function loadHome()
{
    templateBuilder.build('home',{},'main')

    productService.search();
    categoryService.getAllCategories(loadCategories);
}

function editProfile()
{
    profileService.loadProfile();
}

function saveProfile()
{
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const zip = document.getElementById("zip").value;

    const profile = {
        firstName,
        lastName,
        phone,
        email,
        address,
        city,
        state,
        zip
    };

    profileService.updateProfile(profile);
}

function showCart()
{
    cartService.loadCartPage();
}

function clearCart()
{
    cartService.clearCart();
    cartService.loadCartPage();
}

function setCategory(control)
{
    productService.addCategoryFilter(control.value);
    productService.search();

}

function setSubcategory(control)
{
    productService.addSubcategoryFilter(control.value);
    productService.search();

}

function setMinPrice(control)
{
    const value = control.value != 0 ? control.value : "";
    productService.addMinPriceFilter(value);
    productService.search();
}

function setMaxPrice(control)
{
    const value = control.value != 1500 ? control.value : "";
    productService.addMaxPriceFilter(value);
    productService.search();
}

function applyFilters()
{
    setMinPrice(document.getElementById("min-price"));
    setMaxPrice(document.getElementById("max-price"));
    productService.search();
}

function closeError(control)
{
    setTimeout(() => {
        control.click();
    },3000);
}

// AI generated
function showRegisterForm() {
    templateBuilder.build('register', {}, 'login');
}

function register() {
    const user = {
        username: document.getElementById("registerUsername").value,
        password: document.getElementById("registerPassword").value,
        confirmPassword: document.getElementById("confirmPassword").value,
        role: "USER"
    };

    axios.post(`${config.baseUrl}/register`, user)
        .then(response => {
            alert("Account created. You can now log in.");
            showLoginForm();
        })
        .catch(error => {
            console.log(error.response?.data);
            alert("Registration failed.");
        });
}

// Ends here

document.addEventListener('DOMContentLoaded', () => {

    loadHome();
});
