import React from "react";
import { Link, useNavigate } from "react-router-dom"

function BackButton({ onClick }) {
    return (
        <Link to={onClick}
        className="mx-4 inline-flex items-start pr-3 py-1.5 rounded-md text-indigo-500 hover:bg-indigo-50">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" className="h-6 w-6">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 16l-4-4m0 0l4-4m-4 4h18">
            </path>
        </svg>
        </Link>
    );
}


export default BackButton;