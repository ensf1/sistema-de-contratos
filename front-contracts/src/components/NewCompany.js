import {useEffect} from "react";
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
    Stack,
} from "@chakra-ui/react";
import InputMask from "react-input-mask";
import {registerCompany} from "../api";

export default function CompanyForm({ isOpen, onClose, saveCompany, token }) {
  const { register, handleSubmit, reset } = useForm();

  useEffect(() => reset(), [isOpen, reset]);

  const submit = async (data) => {
    const res = await registerCompany(data, token)

    if(res){
      saveCompany(data);
      onClose();
    }    
  };

  return (
    <Drawer size="md" isOpen={isOpen} placement="right" onClose={onClose}>
      <DrawerOverlay />
      <DrawerContent>
        <DrawerCloseButton />
        <DrawerHeader borderBottomWidth="1px">Nova empresa</DrawerHeader>

        <DrawerBody>
          <form onSubmit={handleSubmit(submit)}>
            <Stack spacing="24px" mt={6}>
              <Box>
                <FormLabel htmlFor="name">Nome</FormLabel>
                <Input id="name" {...register("name")} />
              </Box>

              <Box>
                <FormLabel htmlFor="address">Endereço</FormLabel>
                <Input id="address" {...register("address")} />
              </Box>

              <Box>
                <FormLabel htmlFor="cnpj">CNPJ</FormLabel>
                <InputMask
                  mask="99.999.999/9999-99"
                  placeholder="00.000.000/0000-00"
                  {...register("cnpj")}
                >
                  {(inputProps) => <Input {...inputProps} />}
                </InputMask>
              </Box>
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
