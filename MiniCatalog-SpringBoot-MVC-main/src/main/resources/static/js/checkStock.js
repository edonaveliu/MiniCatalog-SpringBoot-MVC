document.addEventListener('DOMContentLoaded', function() {
    async function checkStock(productId, button) {
        button.disabled = true;
        const originalText = button.textContent;
        button.textContent = 'Duke kontrolluar...';

        try {
            await new Promise(resolve => setTimeout(resolve, 500));

            const response = await fetch(`/api/products/check-stock/${productId}`);

            if (!response.ok) {
                throw new Error("Error fetching product stock");
            }

            const stock = await response.json();
            const stockSpan = button.closest('.card-body').querySelector('.stock-value');
            if (stockSpan) {
                stockSpan.textContent = stock;
            }
        } catch (error) {
            console.error("Error checking stock:", error);
            alert("Failed to check stock. Please try again later.");
        } finally {
            button.disabled = false;
            button.textContent = originalText;
        }
    }

    function addEventListeners() {
        const buttons = document.querySelectorAll('.btn-check-stock');
        if (buttons.length === 0) {
            console.warn("No elements found with the class 'btn-check-stock'.");
        }
        buttons.forEach(button => {
            button.addEventListener('click', function() {
                const productId = this.getAttribute('data-product-id');
                checkStock(productId, this);
            });
        });
    }

    addEventListeners();
});
