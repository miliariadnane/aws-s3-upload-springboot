import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { useDropzone } from "react-dropzone";
import "./App.css";

const Users = () => {
  const [users, setUsers] = useState([]);
  const fetchUsers = () => {
    axios.get("http://localhost:8080/api/v1/users").then((res) => {
      console.log(res);
      setUsers(res.data);
    });
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return users.map((user, index) => {
    return (
        <div key={index}>
          {user.userId ? (
              <img
                  src={`http://localhost:8080/api/v1/users/${user.userId}/image/download`}
              />
          ) : null}
          <br />
          <br />
          <h1>{user.username}</h1>
          <p>{user.lastName.toUpperCase()}  {user.firstName}</p>
          <Dropzone {...user} />
          <br />
        </div>
    );
  });
};

function Dropzone({ userId }) {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0];
    console.log(file);

    const formData = new FormData();
    formData.append("file", file);

    axios
        .post(
            `http://localhost:8080/api/v1/users/${userId}/image/upload`,
            formData,
            {
              headers: {
                "content-type": "multipart/form-data",
              },
            }
        )
        .then(() => {
          console.log("file upload successfully");
        })
        .catch((err) => {
          console.log(err);
        });
    // Do something with the files
  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {isDragActive ? (
            <p>Drop the image here ...</p>
        ) : (
            <p>
              Drag 'n' drop profile image here, or click to select profile image
            </p>
        )}
      </div>
  );
}

function App() {
  return (
      <div className="App">
        <Users />
      </div>
  );
}

export default App;
