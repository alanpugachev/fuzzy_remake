"use client";

import { Timestamp } from 'next/dist/server/lib/cache-handlers/types';
import React, { useState } from 'react';

export type User = {
    id: number,
    username: string,
    firstName: string,
    secondName: string
}

export default function AdminPage() {
    return (
        <div className="container">
            <div className="container-wrapper">
                <div className="navbar">
                    <a href="/">
                        <button className="nav-button">
                        Home
                        </button>
                    </a>

                    <a href="/about">
                        <button className="nav-button">
                        About
                        </button>
                    </a>
                </div>

                <main className='content-box'>
                    <h1>admin</h1>
                </main>
            </div>
        </div>
    )
}