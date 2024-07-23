import {useState} from "react";
import {useForm} from "react-hook-form";
import {
    Box,
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent,
    DrawerFooter,
    DrawerHeader,
    DrawerOverlay,
    FormLabel,
    Stack
} from "@chakra-ui/react";
import axios from "axios";

export default function RegisterForm({ isOpen, onClose }) {
  const { register, handleSubmit } = useForm();
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const onSubmit = async (data) => {
    try {
        const response = await axios.post('http://localhost:8080/register', {
            username,
            password,
            name,
            email
        });
        if (response.status === 200) {
            setSuccessMessage('Registration successful!');
            // Clear form inputs
            setUsername('');
            setPassword('');
            setName('');
            setEmail('');
          } else {
            setSuccessMessage('Registration failed. Please try again.');
          }
    } catch (err) {
      setError(err.response?.data?.message || "Registration failed");
    }
  };

  return (
    <Drawer size="md" isOpen={isOpen} placement="right" onClose={onClose}>
      <DrawerOverlay />
      <DrawerContent>
        <DrawerCloseButton />
        <DrawerHeader borderBottomWidth="1px">Register</DrawerHeader>

        <DrawerBody>
          <form onSubmit={handleSubmit(onSubmit)}>
            <Stack spacing="24px" mt={6}>
              <Box>
                <FormLabel htmlFor="username">Username</FormLabel>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
              </Box>
              <Box>
                <FormLabel htmlFor="password">Password</FormLabel>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
              </Box>
              <Box>
                <FormLabel htmlFor="name">Name</FormLabel>
                <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
              </Box>
              <Box>
                <FormLabel htmlFor="email">Email</FormLabel>
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
              </Box>
              {error && <Box color="red.500">{error}</Box>}
            </Stack>
          </form>
          {successMessage && <p>{successMessage}</p>} {/* Display success message */}
        </DrawerBody>

        <DrawerFooter borderTopWidth="1px">
          <Button variant="outline" mr={4} onClick={onClose}>Cancel</Button>
          <Button type="submit" colorScheme="teal" onClick={handleSubmit(onSubmit)}>Register</Button>
        </DrawerFooter>
      </DrawerContent>
    </Drawer>
  );
}
