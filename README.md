# 🛍️ shopAR: Augmented Reality E-Commerce for Android

**shopAR** is an innovative Android mobile application that redefines online shopping by integrating **Augmented Reality (AR)**. Developed using **Java** and **Google's ARCore library**, this app allows users to accurately preview and interact with 3D models of products directly within their physical environment before making a purchase.

## ✨ Features

This application delivers a comprehensive and modern e-commerce solution:

### 1. Immersive AR Shopping
* **Real-time Visualization:** Users can place 3D product models (like furniture, accessories, or appliances) directly onto real-world surfaces using their device's camera.
* **Scale and Fit Check:** Allows customers to verify the size, fit, and aesthetic compatibility of a product with their home or surroundings.

### 2. Full E-Commerce Functionality
* **Product Catalog:** Browse and view detailed information for all available items.
* **Secure Authentication:** User registration and login system for a personalized shopping experience and order tracking.
* **Cart and Checkout:** Standardized workflow for adding items to the cart and completing the purchase.

### 3. Inventory Management (Admin Panel)
* **Dedicated Admin Login:** A separate, secure login system for store managers.
* **Product Management:** Tools to easily add new products, upload 3D models, and update item details.
* **Inventory Control:** Functionality to monitor and manage stock levels in real time.

## 💻 Technology Stack

| Category | Technology | Description |
| :--- | :--- | :--- |
| **Mobile Platform** | **Java** (Android) | Core development language for the native Android application. |
| **Augmented Reality** | **ARCore** (by Google) | Provides the framework for motion tracking and environmental understanding required for stable AR object placement. |
| **Backend/Database** | **Firebase Firestore** | Primary database for storing structured data (products, user profiles, orders). |
| **Real-time Data** | **Firebase Realtime DB** | Used for managing data that requires instantaneous synchronization, such as certain aspects of inventory or live feature updates. |
| **Hosting** | **Google Firebase** | Comprehensive cloud platform for authentication, data storage, and backend logic. |

## 📸 Visual Showcase

To truly showcase the power of AR in e-commerce, explore the following images demonstrating the user experience and administrative capabilities:

### **AR Shopping Experience**
![Screenshot_2021-12-30-18-29-20-36_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/b24f5396-94c3-45c1-83e9-41f92c313c3c)
![Screenshot_2021-12-30-18-29-02-87_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/186ce0ae-beeb-4f2f-843f-faac2f8b16f8)
![Screenshot_2021-12-30-18-28-53-74_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/8cf093dc-21b6-4d53-aa35-cef468876046)
![Screenshot_2021-12-30-18-28-49-88_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/624dc6e6-8d22-4b33-a156-b2cdc8227ae6)
![Screenshot_2021-12-30-18-28-32-49_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/affe53a1-a4a0-484c-b2c5-040151bee6ad)
![Screenshot_2021-12-30-18-28-20-79_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/a0322071-7d63-4bcb-b340-5f232ac6b815)
![Screenshot_2021-12-30-18-28-07-73_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/39a50a7b-bc7f-4abf-9a88-5469fa27e279)
![Screenshot_2021-12-30-18-33-19-90_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/21365834-8b86-42d8-a8a0-62e7f81a0cc5)
![Screenshot_2021-12-30-18-32-59-72_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/6376e35a-9c7d-4689-b9af-d3f0cf0098e9)
![Screenshot_2021-12-30-18-31-34-45_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/b5b6f67d-897d-43d9-aa26-c44b7840630c)
![Screenshot_2021-12-30-18-31-30-24_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/96b265d2-fea5-4f41-97fe-5c58101131a5)
![Screenshot_2021-12-30-18-30-59-18_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/113a8a2e-2b81-4b38-9d5c-c8b4b6ce35ca)
![Screenshot_2021-12-30-18-30-46-19_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/bd91a5d0-0727-40ac-b085-0e90201aefeb)
![Screenshot_2021-12-30-18-29-50-92_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/f6a15cf9-8603-45d9-a00f-15cb55284c20)
![Screenshot_2021-12-30-18-29-29-44_31e4ffeb9d31067e8cbe195dd46800f9](https://github.com/user-attachments/assets/ae5a545f-9ef0-4380-8298-d1577354301c)


## 🚀 Getting Started

To set up and run the `shopAR` application locally, follow these steps:

### Prerequisites

1.  **Android Studio** installed on your system.
2.  An Android device or emulator supporting **ARCore**.
3.  A **Firebase Project** set up to host the backend services (Firestore and Realtime DB).

### Installation and Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/ngoyal813/shopAR.git](https://github.com/ngoyal813/shopAR.git)
    ```
2.  **Open in Android Studio:**
    Navigate to **File > Open** and select the cloned directory.
3.  **Configure Firebase:**
    * Place your `google-services.json` file inside the `app/` module directory.
    * Ensure the database rules in your Firebase console are configured for reading and writing data.
4.  **Run the App:**
    Select an ARCore-compatible device or emulator and click the **Run** button (▶️).

---

## 🤝 Contribution

Contributions are welcome! If you have suggestions or find bugs, please feel free to open an issue or submit a pull request.
