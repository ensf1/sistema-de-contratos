import {useEffect, useState} from "react";
import RegisterForm from "./components/RegisterForm";
import LoginForm from "./components/LoginForm";
import {
    Box,
    Button,
    Card,
    CardBody,
    Flex,
    Icon,
    Stack,
    Stat,
    StatLabel,
    StatNumber,
    useDisclosure,
} from "@chakra-ui/react";
import {HiOutlineBriefcase, HiOutlineCalendar, HiOutlineDocumentText, HiOutlinePlus,} from "react-icons/hi";
import CompanyForm from "./components/NewCompany";
import ContractForm from "./components/ContractForm";
import Table from "./components/ContractTable";
import {getAllCompanies, getAllContracts} from "./api";

function App() {
  const {
    isOpen: isCompanyOpen,
    onOpen: onCompanyOpen,
    onClose: onCompanyClose,
  } = useDisclosure();
  const {
    isOpen: isContractOpen,
    onOpen: onContractOpen,
    onClose: onContractClose,
  } = useDisclosure();

  const [isRegisterOpen, setRegisterOpen] = useState(false);
  const [isLoginOpen, setLoginOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userName, setUserName] = useState("");
  const [token, setToken] = useState("");

  const [companies, setCompanies] = useState([]);
  const [contracts, setContracts] = useState([]);

  async function hydrate(token) {
    try {
      const allCompanies = await getAllCompanies(token);
      const allContracts = await getAllContracts(token);
  
      if (allCompanies.length > 0) {
        setCompanies(allCompanies);
      }
  
      if (allContracts.length > 0) {
        setContracts(allContracts);
      }
    } catch (error) {
      console.error("Error in hydrate function:", error); // Debug log
    }
  }
  

  useEffect(() => {
    if (isLoggedIn) {
      hydrate(token);
    }
  }, [isLoggedIn, token]);

  useEffect(() => {
    async function reqAllCompanies(token) {
      if (token) {
        const allCompanies = await getAllCompanies(token);
        if (allCompanies.length > 0) {
          setCompanies(allCompanies);
        }
      }
    }
    async function reqAllContracts(token) {
      if (token) {
        const allContracts = await getAllContracts(token);
        if (allContracts.length > 0) {
          setContracts(allContracts);
        }
      }
    }
  
    reqAllContracts(token);
    reqAllCompanies(token);
  }, [token]);

  const handleSaveCompany = (data, token) => {
    setCompanies((prevState) => [...prevState, data]);
  };

  const handleSaveContract = (data, token) => {
    setContracts((prevState) => [...prevState, data]);
  };

  const handleDeleteContract = (contractNumber, token) => {
    setContracts((prevState) => prevState.filter((contract) => contract.number !== contractNumber));
  };

  const handleLogin = (token) => {
    console.log("Received token in handleLogin:", token); // Debug log
    if (token) {
      setIsLoggedIn(true);
      setToken(token);
    } else {
      console.error("Token is empty or invalid"); // Debug log
    }
  };
  
  
  const handleLogout = () => {
    setIsLoggedIn(false);
    setToken("");
  };

  if (!isLoggedIn) {
    return (
      <div className="App">
        <Stack spacing={4} align="center" mt={10}>
          <Button colorScheme="teal" variant="outline" onClick={() => setRegisterOpen(true)}>
            Register
          </Button>
          <Button colorScheme="teal" onClick={() => setLoginOpen(true)}>
            Login
          </Button>
        </Stack>

        <RegisterForm isOpen={isRegisterOpen} onClose={() => setRegisterOpen(false)} />
        <LoginForm isOpen={isLoginOpen} onClose={() => setLoginOpen(false)} onLogin={handleLogin} />
      </div>
    );
  }

  return (
    <div className="App">
      <Flex bg="teal.500" p={4} color="white" justify="space-between">
        <Button colorScheme="teal" variant="outline" onClick={handleLogout}>
          Logout
        </Button>
      </Flex>

      <CompanyForm
        isOpen={isCompanyOpen}
        onOpen={onCompanyOpen}
        onClose={onCompanyClose}
        saveCompany={handleSaveCompany}
        token={token}
      />
      <ContractForm
        companies={companies}
        isOpen={isContractOpen}
        onOpen={onContractOpen}
        onClose={onContractClose}
        saveContract={handleSaveContract}
        saveCompany={handleSaveCompany}
        token={token}
      />


      <Stack display="flex" mt={4}>
        <Stack
          mb={6}
          spacing={4}
          display="flex"
          direction="row"
          justifyContent="flex-start"
        >
          <Button colorScheme="teal" variant="outline" onClick={onCompanyOpen}>
            Cadastrar empresa
          </Button>
          <Button
            variant="solid"
            colorScheme="teal"
            onClick={onContractOpen}
            leftIcon={<HiOutlinePlus />}
          >
            Novo contrato
          </Button>
        </Stack>

        <Stack spacing="4" flexDirection="row" justifyContent="flex-start">
          <Card variant="filled">
            <CardBody>
              <Box display="flex" flexDirection="row" alignItems="center">
                <Box
                  p="2"
                  mr="4"
                  bg="teal.500"
                  color="white"
                  borderRadius="md"
                  display="inline-flex"
                >
                  <Icon as={HiOutlineDocumentText} boxSize="6" />
                </Box>
                <Stat>
                  <StatLabel>Quantidade de contratos</StatLabel>
                  <StatNumber>{contracts.length}</StatNumber>
                </Stat>
              </Box>
            </CardBody>
          </Card>
          <Card variant="filled">
            <CardBody>
              <Box display="flex" flexDirection="row" alignItems="center">
                <Box
                  p="2"
                  mr="4"
                  bg="teal.500"
                  color="white"
                  borderRadius="md"
                  display="inline-flex"
                >
                  <Icon as={HiOutlineCalendar} boxSize="6" />
                </Box>
                <Stat>
                  <StatLabel>Contratos vencidos</StatLabel>
                  <StatNumber>0</StatNumber>
                </Stat>
              </Box>
            </CardBody>
          </Card>
          <Card variant="filled">
            <CardBody>
              <Box display="flex" flexDirection="row" alignItems="center">
                <Box
                  p="2"
                  mr="4"
                  bg="teal.500"
                  color="white"
                  borderRadius="md"
                  display="inline-flex"
                >
                  <Icon as={HiOutlineBriefcase} boxSize="6" />
                </Box>
                <Stat>
                  <StatLabel>Empresas cadastradas</StatLabel>
                  <StatNumber>{companies.length}</StatNumber>
                </Stat>
              </Box>
            </CardBody>
          </Card>
        </Stack>
      </Stack>

      <Table contracts={contracts} deleteContract={handleDeleteContract} token={token} />
    </div>
  );
}

export default App;
