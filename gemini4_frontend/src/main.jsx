import React from 'react'
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Register from './components/register.jsx'
import Home from './components/home.jsx'

const router = createBrowserRouter([
  {
    path:"/",
    element: <App />
  },
  {
    path:"register",
    element: <Register />
  },
  {
    path:"home",
    element: <Home />
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
