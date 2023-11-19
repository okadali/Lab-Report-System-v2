export const getProperYear = (date) => {
  try {
    const objectDate = new Date(date)
    return objectDate.getDate() + "/" + (objectDate.getMonth() + 1) + "/" + objectDate.getFullYear()
  }
  catch (e) {
    return "Invalid Date"
  }
}