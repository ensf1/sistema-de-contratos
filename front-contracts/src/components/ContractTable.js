import moment from "moment";
import {
    Button,
    Modal,
    ModalBody,
    ModalCloseButton,
    ModalContent,
    ModalFooter,
    ModalHeader,
    ModalOverlay,
    Table,
    TableContainer,
    Tbody,
    Td,
    Text,
    Th,
    Thead,
    Tr,
    useDisclosure,
} from "@chakra-ui/react";
import {HiOutlineTrash} from "react-icons/hi";
import {useState} from "react";
import {deleteContract as deleteContractReq} from "../api";

const CONTRACT_TYPES = {
  UNINFORMED: "Não informado",
  SERVICES: "Serviços",
  GOODS_AND_MATERIALS: "Bens E Materiais",
};

export default function ContractTable({ contracts, deleteContract, token }) {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [contractId, setContractId] = useState();
  
  const handleDeleteContract = async () => {
    const deleteRes = await deleteContractReq(contractId, token);
    if (deleteRes === 204){
      deleteContract(contractId, token);
      onClose();
    }
  };

  const handleOpenDeleteModal = (contractNumber) => {
    onOpen();
    setContractId(contractNumber)
  }

  return (
    <>
      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Excluir Contrato</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            Tem certeza de que deseja excluir este contrato?
          </ModalBody>

          <ModalFooter>
            <Button variant="ghost" mr={3} onClick={onClose}>
              Cancelar
            </Button>
            <Button variant="ghost" colorScheme="red" onClick={handleDeleteContract}>
              Excluir
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
      
      <TableContainer mt={12}>
        <Table size="lg">
          <Thead>
            <Tr>
              <Th>Título</Th>
              <Th>Contrato</Th>
              <Th>Tipo</Th>
              <Th>Contratada</Th>
              <Th>Representante</Th>
              <Th>Data inicial</Th>
              <Th>Data final</Th>
              <Th>Próximo pagamento</Th>
              <Th></Th>
            </Tr>
          </Thead>
          <Tbody>
            {contracts.length > 0 &&
              contracts.map((contract) => (
                <Tr key={contract.number}>
                  <Td>{contract?.title}</Td>
                  <Td fontWeight="600">{contract?.number}</Td>
                  <Td>{CONTRACT_TYPES["SERVICES"]}</Td>
                  <Td>
                    <Text>{contract?.contractedCompany?.name}</Text>
                    <Text fontSize="14px">
                      {contract.contractedCompany.address}
                    </Text>
                    <Text fontWeight="600" fontSize="14px">
                      {contract.contractedCompany.cnpj.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, "$1.$2.$3/$4-$5")}
                    </Text>
                  </Td>
                  <Td>
                    <Text>{contract?.representative?.name}</Text>
                    <Text fontWeight="600" fontSize="14px">
                      {contract?.representative?.email}
                    </Text>
                  </Td>
                  <Td>{contract.start}</Td>
                  <Td>{contract.end}</Td>
                  <Td
                    fontWeight="600"
                    color={
                      moment().isAfter(contract.nextPayment) ? "red.500" : "teal.500"
                    }
                  >
                    {contract.nextPayment}
                  </Td>

                  <Td>
                    <Button variant="ghost" colorScheme="red" onClick={() => handleOpenDeleteModal(contract.number)}>
                      <HiOutlineTrash />
                    </Button>
                  </Td>
                </Tr>
              ))}
          </Tbody>
        </Table>
      </TableContainer>
    </>
  );
}
