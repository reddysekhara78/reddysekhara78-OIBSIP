document.addEventListener("DOMContentLoaded", function () {

    fetch("/api/dashboard/analytics")
        .then(response => response.json())
        .then(data => {document.getElementById("depositTotal").innerText =
    "₹" + data.totalDeposit;

document.getElementById("withdrawTotal").innerText =
    "₹" + data.totalWithdrawal;

document.getElementById("transferTotal").innerText =
    "₹" + data.totalTransfer;

            new Chart(document.getElementById("barChart"), {

                type: "bar",

                data: {

                    labels: ["Deposit", "Withdraw", "Transfer"],

                    datasets: [{
                        label: "Amount",
                        data: [
                            data.totalDeposit,
                            data.totalWithdrawal,
                            data.totalTransfer
                        ]
                    }]
                },

                options: {

                    responsive: true

                }

            });


            new Chart(document.getElementById("pieChart"), {

                type: "pie",

                data: {

                    labels: ["Deposit", "Withdraw", "Transfer"],

                    datasets: [{

                        data: [
                            data.totalDeposit,
                            data.totalWithdrawal,
                            data.totalTransfer
                        ]

                    }]
                },

                options: {

                    responsive: true

                }

            });

        });

});