import {useEffect, useState} from "react";
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
    Input,
    Select,
    Stack,
} from "@chakra-ui/react";
import InputMask from "react-input-mask";
import {registerContractAndCompany, registerContractForExistingCompany} from "../api";

export default function ContractForm({ isOpen, onClose, companies = [], saveContract, saveCompany, token }) {
  const { register, handleSubmit, reset } = useForm();

  const [isNewCompany, setIsNewCompany] = useState(false);

  useEffect(() => {
    reset()
    setIsNewCompany(false)
  }, [isOpen, reset]);

  const submit = async (data) => {
    console.log("TOKEN ", token)
    if (isNewCompany){
      const res = await registerContractAndCompany(data,token)
      
      if (res){
        saveContract(res);
        saveCompany(res.contractedCompany)
        onClose();
      }    
    } else{
      const findCompany  = companies.find((company) => company.cnpj === data.company.cnpj)
      delete data.company
      data.contractedCompany = findCompany
      console.log(data)
      const res = await registerContractForExistingCompany(data.contractedCompany.cnpj, data, token)
      if (res){
        saveContract(res);
        onClose();
      } 
    }
  };

  const handleSetIsNewCompany = () => setIsNewCompany(!isNewCompany);

  return (
    <Drawer size="md" isOpen={isOpen} placement="right" onClose={onClose}>
      <DrawerOverlay />
      <DrawerContent>
        <DrawerCloseButton />
        <DrawerHeader borderBottomWidth="1px">Novo contrato</DrawerHeader>

        <DrawerBody>
          <form onSubmit={handleSubmit(submit)}>
            <Stack spacing="24px" mt={6}>
              <Box>
                <FormLabel htmlFor="title">Título</FormLabel>
                <Input id="title" {...register("title")} />
              </Box>

              <Box>
                <FormLabel htmlFor="number">Número do contrato</FormLabel>
                <Input id="number" {...register("number")} />
              </Box>

              <Box>
                <FormLabel htmlFor="type">Tipo de contrato</FormLabel>
                <Select
                  id="type"
                  {...register("type")}
                  defaultValue="UNINFORMED"
                >
                  <option value="UNINFORMED">-</option>
                  <option value="SERVICES">Serviços</option>
                  <option value="GOODS_AND_MATERIALS">Bens E Materiais</option>
                </Select>
              </Box>

              <Box>
                <Box
                  mb={2}
                  display="flex"
                  flexDirection="row"
                  alignItems="center"
                  justifyContent="space-between"
                >
                  <FormLabel htmlFor="contractedCompany">Empresa</FormLabel>
                  {isNewCompany && (
                    <Button variant="link" onClick={handleSetIsNewCompany}>
                      cancelar
                    </Button>
                  )}
                </Box>

                {isNewCompany ? (
                  <Box p={4} bg="gray.50" borderRadius={6}>
                    <Box mt={4}>
                      <FormLabel htmlFor="name" fontSize="14px">
                        Nome
                      </FormLabel>
                      <Input
                        id="name"
                        {...register("contractedCompany.name")}
                      />
                    </Box>

                    <Box mt={4}>
                      <FormLabel htmlFor="address" fontSize="14px">
                        Endereço
                      </FormLabel>
                      <Input
                        id="address"
                        {...register("contractedCompany.address")}
                      />
                    </Box>

                    <Box mt={4}>
                      <FormLabel htmlFor="cnpj" fontSize="14px">
                        CNPJ
                      </FormLabel>
                      <InputMask
                        mask="99.999.999/9999-99"
                        placeholder="00.000.000/0000-00"
                        {...register("contractedCompany.cnpj")}
                      >
                        {(inputProps) => <Input {...inputProps} />}
                      </InputMask>
                    </Box>
                  </Box>
                ) : (
                  <>
                    <Select
                      id="company"
                      {...register("company.cnpj")}
                      defaultValue="UNINFORMED"
                    >
                      <option value="UNINFORMED">-</option>
                      {companies.map((company) => (
                        <option key={company.cnpj} value={company.cnpj}>
                          {company?.name}
                        </option>
                      ))}
                    </Select>
                    <Button
                      variant="ghost"
                      mt={4}
                      onClick={handleSetIsNewCompany}
                    >
                      + nova empresa
                    </Button>
                  </>
                )}
              </Box>

              <Box>
                <FormLabel htmlFor="representativeName">
                  Nome do representante
                </FormLabel>
                <Input
                  id="representativeName"
                  {...register("representative.name")}
                  placeholder=""
                />
              </Box>

              <Box>
                <FormLabel htmlFor="representativeEmail">
                  E-mail do representante
                </FormLabel>
                <Input
                  id="representativeEmail"
                  {...register("representative.email")}
                  placeholder=""
                />
              </Box>

              <Stack
                display="flex"
                flexDirection="row"
                justifyContent="space-between"
              >
                <Box width="50%">
                  <FormLabel htmlFor="start">Data inicial</FormLabel>
                  <InputMask
                    mask="99-99-9999"
                    placeholder="DD-MM-AAAA"
                    {...register("start")}
                  >
                    {(inputProps) => <Input {...inputProps} />}
                  </InputMask>
                </Box>

                <Box width="50%">
                  <FormLabel htmlFor="end">Data final</FormLabel>
                  <InputMask
                    mask="99-99-9999"
                    placeholder="DD-MM-AAAA"
                    {...register("end")}
                  >
                    {(inputProps) => <Input {...inputProps} />}
                  </InputMask>
                </Box>
              </Stack>
            </Stack>
          </form>
        </DrawerBody>

        <DrawerFooter borderTopWidth="1px">
          <Button variant="outline" mr={4} onClick={onClose}>
            Cancelar
          </Button>
          <Button
            type="submit"
            colorScheme="teal"
            onClick={handleSubmit(submit)}
          >
            Salvar
          </Button>
        </DrawerFooter>
      </DrawerContent>
    </Drawer>
  );
}
