import axios from "axios";


const baseUrl = "http://127.0.0.1:8080";
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.common['Access-Control-Allow-Methods'] = 'GET, POST, PUT, DELETE, OPTIONS';
axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'Content-Type';

const registerCompany = async (data, token) => {
  console.log(data)
  try {
    data.cnpj = data.cnpj.replace(/\D/g, "")
    const response = await axios.post(`${baseUrl}/v1/companies`, data, {
      headers: {
        "Content-Type": "application/json",
        'Authorization': `Bearer ${token}`
      },
    });

    return response.data;
  } catch (error) {
    console.log(error)
    return null;
  }
};

const registerContractAndCompany = async (data,token) => {
  try {
    data.contractedCompany.cnpj = data.contractedCompany.cnpj.replace(/\D/g, "")
    const response = await axios.post(`${baseUrl}/v1/contracts`,
      data, {
      headers: {
        "Content-Type": "application/json",
        'Authorization': `Bearer ${token}`
      }
    });

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const registerContractForExistingCompany = async (cnpj, data, token) => {
  try {
    console.log("token request ", token);
    const formatCnpj = cnpj.replace(/\D/g, "")
    const response = await axios.post(
      `${baseUrl}/v1/companies/${formatCnpj}/contracts`,
      data, {
      headers: {
        "Content-Type": "application/json",
        'Authorization': `Bearer ${token}`
      }
    });

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const getAllCompanies = async (token) => {
  try {
    console.log("Requesting companies with token:", token); // Debug log
    const response = await axios.get(`${baseUrl}/v1/companies`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    console.log("Companies response:", response.data); // Debug log
    return response.data;
  } catch (error) {
    console.error("Error fetching companies:", error); // Debug log
    return [];
  }
};


const getAllContracts = async (token) => {
  try {
    console.log("Requesting contracts with token:", token); // Debug log
    const response = await axios.get(`${baseUrl}/v1/contracts`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    console.log("Contracts response:", response.data); // Debug log
    return response.data;
  } catch (error) {
    console.error("Error fetching contracts:", error); // Debug log
    return [];
  }
};

const updateContractEndValidity = async (contractNumber, newEndDate, token) => {
  try {
    const response = await axios.patch(
      `${baseUrl}/v1/contracts/${contractNumber}`,
      {
        end: newEndDate,
      },
      {
        headers: {
          "Content-Type": "application/json",
          'Authorization': `Bearer ${token}`
        },
      }
    );

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const updateContractRepresentative = async (
  contractNumber,
  newRepresentative,
  token
) => {
  try {
    const response = await axios.patch(
      `${baseUrl}/v1/contracts/${contractNumber}`,
      {
        representative: newRepresentative,
      },
      {
        headers: {
          "Content-Type": "application/json",
          'Authorization': `Bearer ${token}`
        },
      }
    );

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const deleteContract = async (contractNumber, token) => {
  try {
    const response = await axios.delete(
      `${baseUrl}/v1/contracts/${contractNumber}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`
        },
      }
    );

    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export {
  registerCompany,
  registerContractAndCompany,
  registerContractForExistingCompany,
  getAllCompanies,
  getAllContracts,
  updateContractEndValidity,
  updateContractRepresentative,
  deleteContract,
};
