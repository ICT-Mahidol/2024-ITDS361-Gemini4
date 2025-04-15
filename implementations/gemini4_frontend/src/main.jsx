import React from 'react'
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Register from './components/register.jsx'
import Home from './components/home.jsx'
import Cli from './components/cli.jsx'
import Configuration from './components/configuration.jsx'

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
  },
  {
    path:"home/cli",
    element: <Cli />
  },
  {
    path:"/home/configuration",
    element: <Configuration />
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
