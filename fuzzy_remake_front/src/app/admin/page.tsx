"use client";

import React, { useState, useEffect } from 'react';

export type User = {
    id: number,
    username: string,
    firstName: string,
    secondName: string
}

export default function AdminPage() {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await fetch('http://localhost:8080/admin/allUsers');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                setUsers(data);
            } catch (err) {
                setError(err instanceof Error ? err.message : 'An unknown error occurred');
            } finally {
                setLoading(false);
            }
        };

        fetchUsers();
    }, []);

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
                    <h1>Admin Panel</h1>
                    
                    {loading && <p>Loading users...</p>}
                    
                    {error && <p className="error-message">Error: {error}</p>}
                    
                    {!loading && !error && (
                        <div className="users-table-container">
                            <table className="users-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Username</th>
                                        <th>First Name</th>
                                        <th>Second Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {users.length > 0 ? (
                                        users.map(user => (
                                            <tr key={user.id}>
                                                <td>{user.id}</td>
                                                <td>{user.username}</td>
                                                <td>{user.firstName}</td>
                                                <td>{user.secondName}</td>
                                            </tr>
                                        ))
                                    ) : (
                                        <tr>
                                            <td colSpan={4} className="no-users">No users found</td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                    )}
                </main>
            </div>
        </div>
    )
}