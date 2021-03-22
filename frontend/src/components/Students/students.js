import React from 'react';
const students = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Username</th>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Password</th>
                            <th scope={"col"}>Surname</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.students.map((term) => {
                            return (
                                <tr>
                                    <td>{term.username}</td>
                                    <td>{term.name}</td>
                                    <td>{term.password}</td>
                                    <td>{term.surname}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default students;
