import React, {useEffect, useState} from 'react';
import {
    Button,
    FormControl,
    FormLabel,
    Input,
    Modal,
    ModalBody,
    ModalCloseButton,
    ModalContent,
    ModalFooter,
    ModalHeader,
    ModalOverlay
} from '@chakra-ui/react';
import axios from 'axios';

const LoginForm = ({ isOpen, onClose, onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [backendMessage, setBackendMessage] = useState('');

  useEffect(() => {
    const fetchMessage = async () => {
      try {
        const response = await axios.get('http://localhost:8080/message');
        setBackendMessage(response.data);
      } catch (error) {
        console.error('There was an error fetching the message!', error);
        setBackendMessage('Failed to load message from the server.');
      }
    };

    fetchMessage();
  }, []);

  const handleLogin = async (event) => {
    event.preventDefault(); // Prevent the default form submission  

    try {
      const response = await axios.post('http://localhost:8080/login', {
        username,
        password
      });
      console.log("Login response data:", response.data);
      if (response.status === 200) {
        const { token } = response.data;
        localStorage.setItem('token', token); 
        onLogin(token); 
        setErrorMessage('');
        onClose();
      } else {
        setErrorMessage('Login failed. Please check your credentials.');
      }
    } catch (error) {
      console.error('There was an error logging in!', error);
      setErrorMessage('Login failed. Please check your credentials.');
    }
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Login</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <p>{backendMessage}</p>
          <form onSubmit={handleLogin}>
            <FormControl id="username" isRequired>
              <FormLabel>Username</FormLabel>
              <Input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </FormControl>
            <FormControl id="password" isRequired mt={4}>
              <FormLabel>Password</FormLabel>
              <Input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </FormControl>
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
          </form>
        </ModalBody>
        <ModalFooter>
          <Button colorScheme="blue" mr={3} onClick={handleLogin}>
            Login
          </Button>
          <Button variant="ghost" onClick={onClose}>Cancel</Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};

export default LoginForm;
