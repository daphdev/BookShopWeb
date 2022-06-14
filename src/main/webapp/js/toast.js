// COMPONENTS
export function toastComponent(message, color = "primary") {
  return `
    <div class="toast align-items-center text-white bg-${color} border-0" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
          ${message}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  `;
}

// UTILS
function _showToast() {
  const hiddenToastElements = [].slice.call(document.querySelectorAll(".toast.hide"));
  hiddenToastElements.forEach((hiddenToastElement) => hiddenToastElement.remove());

  const toastElements = [].slice.call(document.querySelectorAll(".toast:not(.hide)"));
  const toasts = toastElements.map((toastElement) => new bootstrap.Toast(toastElement));
  toasts.forEach((toast) => toast.show());
}

// MAIN
function createToast(toastComponent) {
  const toastContainerElement = document.querySelector(".toast-container");
  toastContainerElement.insertAdjacentHTML("beforeend", toastComponent);
  _showToast();
}

export default createToast;
