const editButton = document.getElementById("edit-username");

editButton.addEventListener("click", () => {
  document.getElementById("username-container").style.display = "none";
  document.getElementById("username-input").classList.remove("invisible");
});