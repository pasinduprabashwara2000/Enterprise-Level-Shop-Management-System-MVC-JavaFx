Use Case Documentation

---

## 👥 Actors

| Actor                     | Description                                                      |
| ------------------------- | ---------------------------------------------------------------- |
| **Admin**                 | Manages users, roles, and system settings.                       |
| **Cashier / Sales Clerk** | Handles sales, payments, and returns.                            |
| **Inventory Manager**     | Manages stock levels, purchase orders, and supplier relations.   |
| **Customer**              | Purchases products and may participate in promotions or returns. |
| **Supplier**              | Provides products through purchase orders.                       |

---

## 🛒 Use Case 1 — Manage Users & Roles

**Actors:** Admin
**Description:** Admin creates, updates, or deactivates user accounts and assigns roles.

### Main Flow:

1. Admin logs into the system.
2. Navigates to *User Management*.
3. Adds a new user with username, password, and role.
4. System validates and stores the new user record.
5. Admin can edit or deactivate existing users.

### Postconditions:

* User record is created or updated.
* Role assignment is reflected in the database.

### Alternate Flow:

* If username already exists, show error message and prevent creation.

---

## 💰 Use Case 2 — Process a Sale

**Actors:** Cashier / Customer
**Description:** A customer purchases one or more products.

### Main Flow:

1. Cashier selects *New Sale*.
2. Adds products by barcode/SKU.
3. System retrieves product details and calculates subtotal, tax, and discounts.
4. Cashier confirms sale and accepts payment.
5. System records sale, updates inventory, and prints receipt.

### Postconditions:

* Sale and payment records created.
* Inventory quantities decreased.

### Alternate Flow:

* If insufficient stock, show warning and block sale completion.

---

## 💳 Use Case 3 — Record Payment

**Actors:** Cashier
**Description:** Process customer payments linked to a sale.

### Main Flow:

1. Cashier selects payment method (cash, card, etc.).
2. Enters amount and reference number (if applicable).
3. System validates amount and records payment.

### Postconditions:

* Payment entry is linked to sale.
* Sale marked as *Paid* if total received >= grand total.

### Alternate Flow:

* If partial payment received, sale status set to *Partially Paid*.

---

## 📦 Use Case 4 — Manage Inventory

**Actors:** Inventory Manager
**Description:** Update product quantities and reorder items as needed.

### Main Flow:

1. Manager opens *Inventory Module*.
2. System displays current stock levels and reorder alerts.
3. Manager can adjust stock manually or record received goods.
4. System updates inventory quantities and timestamps.

### Postconditions:

* Inventory reflects accurate quantities.
* Reorder triggers generated if below threshold.

---

## 🧾 Use Case 5 — Create Purchase Order

**Actors:** Inventory Manager, Supplier
**Description:** Manager creates and sends purchase orders to suppliers.

### Main Flow:

1. Manager selects *Create Purchase Order*.
2. Adds supplier and products with required quantities.
3. System calculates total cost and expected date.
4. Manager submits PO for approval.
5. System saves PO and marks as *Pending*.

### Postconditions:

* PO stored in database.
* Linked to supplier and awaiting delivery.

### Alternate Flow:

* If supplier inactive, PO creation blocked.

---

## 📥 Use Case 6 — Receive Goods

**Actors:** Inventory Manager
**Description:** Record goods received from suppliers.

### Main Flow:

1. Manager selects *Receive Goods*.
2. Finds relevant Purchase Order.
3. Enters received quantities.
4. System updates inventory and PO status.

### Postconditions:

* Inventory increased.
* PO marked as *Completed* or *Partially Received*.

---

## 🎁 Use Case 7 — Apply Promotions

**Actors:** Admin / Cashier
**Description:** Manage and apply discounts or promotions to products.

### Main Flow:

1. Admin defines new promotion (percentage, fixed, BOGO, etc.).
2. Links promotion to specific product or category.
3. System activates promotion within defined period.
4. During sale, cashier adds item → system auto-applies valid promotion.

### Postconditions:

* Promotion rules stored and applied during sales.

---

## 🔄 Use Case 8 — Handle Returns & Refunds

**Actors:** Cashier / Customer
**Description:** Process a return and issue a refund.

### Main Flow:

1. Cashier searches for original sale.
2. Selects items to return.
3. Enters return reason.
4. System validates and updates inventory.
5. Refund processed and recorded.

### Postconditions:

* Return record created.
* Refund amount issued.
* Inventory updated.

### Alternate Flow:

* If return period expired, system blocks the process.

---

## 👥 Use Case 9 — Manage Customers

**Actors:** Cashier / Admin
**Description:** Add or update customer profiles.

### Main Flow:

1. User opens *Customer Management*.
2. Adds or edits customer information.
3. System validates fields and saves record.

### Postconditions:

* Customer record created or updated.
* Linked to sales and loyalty programs.

---

## 🧾 Use Case 10 — Generate Reports

**Actors:** Admin / Manager
**Description:** Generate system reports (sales, inventory, revenue, etc.).

### Main Flow:

1. User selects report type and date range.
2. System aggregates relevant data.
3. Displays charts, tables, or exports CSV/PDF.

### Postconditions:

* Report generated successfully.

---

## ✅ Summary Table

| ID   | Use Case              | Primary Actor      | Key Outcome                       |
| ---- | --------------------- | ------------------ | --------------------------------- |
| UC1  | Manage Users & Roles  | Admin              | User access managed               |
| UC2  | Process Sale          | Cashier            | Sale recorded & inventory reduced |
| UC3  | Record Payment        | Cashier            | Payment stored & linked to sale   |
| UC4  | Manage Inventory      | Inventory Manager  | Stock updated                     |
| UC5  | Create Purchase Order | Inventory Manager  | PO created                        |
| UC6  | Receive Goods         | Inventory Manager  | Inventory increased               |
| UC7  | Apply Promotions      | Admin / Cashier    | Discounts applied                 |
| UC8  | Handle Returns        | Cashier / Customer | Refund processed                  |
| UC9  | Manage Customers      | Cashier / Admin    | Customer record updated           |
| UC10 | Generate Reports      | Admin / Manager    | Business insights generated       |

---

**📅 Last Updated:** October 2025
**📘 Author:** Pasindu Prabashwara
**💾 System:** Retail / POS Management System

