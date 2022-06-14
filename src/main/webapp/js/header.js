// STATIC DATA
const contextPathMetaTag = document.querySelector("meta[name='contextPath']");
const currentUserIdMetaTag = document.querySelector("meta[name='currentUserId']");

// ROOTS/ELEMENTS
const totalCartItemsQuantityRootElement = document.querySelector("#total-cart-items-quantity");

// UTILS
async function _fetchGetCart() {
  const response = await fetch(contextPathMetaTag.content + "/cartItem?userId=" + currentUserIdMetaTag.content, {
    method: "GET",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
    },
  });
  return [response.status, await response.json()];
}

// STATE
const state = {
  totalCartItemsQuantity: 0,
  setTotalCartItemsQuantity: (value) => {
    if (typeof value === "string") {
      state.totalCartItemsQuantity += Number(value);
    } else {
      state.totalCartItemsQuantity = value.cartItems
        .map((cartItem) => cartItem.quantity)
        .reduce((partialSum, cartItemQuantity) => partialSum + cartItemQuantity, 0);
    }
    render();
  },
  initState: async () => {
    const [status, data] = await _fetchGetCart();
    if (status === 200) {
      state.setTotalCartItemsQuantity(data);
    }
  },
}

// RENDER
function render() {
  totalCartItemsQuantityRootElement.innerHTML = state.totalCartItemsQuantity;
}

// MAIN
if (currentUserIdMetaTag) {
  void state.initState();
}

export const setTotalCartItemsQuantity = state.setTotalCartItemsQuantity;
